package com.mooneyserver.account.lookup;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.mooneyserver.account.logging.AccountsLoggingConstants;
import com.mooneyserver.account.ui.view.AbstractBaseView;

/**
 * Static class for performing JNDI
 * lookups and returning the instantiated
 * class. This class is to be used to
 * instantiate fields annotated with
 * the BusinessProcess annotation
 */
public class BackendServiceLookup {
	
	private static Logger log = Logger.getLogger(AccountsLoggingConstants.LOG_AREA_JNDI_LOOKUP);
	
	static Properties props = new Properties();
    static InitialContext ctx;
   
    // Hold JNDI lookup info for all backend services
    static Map<String, String> backendServicesLookup;
    
    // Cache to store backend services and save
    // from performing unnecessary lookups
    static Map<String, Object> cacheBackendServices;
   
    static {
        props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("org.omg.CORBA.ORBInitialHost", "localhost");
       
        props.setProperty("org.omg.CORBA.ORBInitialPort", "3700"); // 3700 is the Glassfish default
       
        try {
            ctx = new InitialContext(props);
        } catch (NamingException e) {
            log.severe("JNDI Lookup error");
            log.log(Level.SEVERE, "Exception:", e);
        }
       
        cacheBackendServices = new HashMap<>();
        
        // Store our backend services for lookup
        backendServicesLookup = new HashMap<>();
        backendServicesLookup.put("com.mooneyserver.account.businesslogic.user.IUserService", 
        		"/Accounts/AccountsBackend/UserBusinessService");
    }
   
    // Check if the service is already cached
    // If not, perform the lookup and return the object
    private static Object getService(String publicServiceCanonicalName) throws NamingException {
        if (!cacheBackendServices.containsKey(publicServiceCanonicalName)) {
            for (String key : backendServicesLookup.keySet()) {
                if (key.equalsIgnoreCase(publicServiceCanonicalName)) {
                    cacheBackendServices.put(publicServiceCanonicalName,
                            ctx.lookup("java:global" + backendServicesLookup.get(key) + "!" + key));
                }
            }
        }
       
        return cacheBackendServices.get(publicServiceCanonicalName);
    }
   
    /**
     * For any view loaded in the DisplayManager,
     * this method will parse any field annotations
     * and inject the Backend Service if a BusinessProcess
     * annotation is discovered
     * 
     * @param view
     * 		<b>AbstractBaseView</b> The view to be loaded by the DisplayManager
     */
    public static void injectBackendServices(AbstractBaseView view) {
        for (Field field : view.getClass().getDeclaredFields()) {
            Annotation annotation = field.getAnnotation(BusinessProcess.class);
           
            if(annotation instanceof BusinessProcess){
                boolean fieldStateChange = false;
                if(!field.isAccessible()) {
                    field.setAccessible(true);
                    fieldStateChange = true;
                }
                   
               
                try {
                    field.set(view, getService(field.getType().getCanonicalName()));
                } catch (IllegalArgumentException | IllegalAccessException | NamingException e) {
                	log.severe("Reflective Field Access Error");
                    log.log(Level.SEVERE, "Exception:", e);
                } finally {
                    if (fieldStateChange) {
                        field.setAccessible(false);
                    }
                }
            }
        }
    }
}