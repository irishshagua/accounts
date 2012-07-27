package com.mooneyserver.account;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.vaadin.jonatan.contexthelp.ContextHelp;

import com.mooneyserver.account.i18n.AccountsMessages;
import com.mooneyserver.account.ui.iface.IAccountsView;
import com.mooneyserver.account.ui.manager.DisplayManager;
import com.mooneyserver.account.ui.view.user.AccountsLoginView;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.HttpServletRequestListener;
import com.vaadin.ui.Window;

public final class AccountsApplication extends Application implements HttpServletRequestListener {


	private static final long serialVersionUID 
		= -1L;
	
	/* Set a specific instance of the App for the connected 
	 * session 
	 */
	private static ThreadLocal<AccountsApplication> sessionInstance = 
			new ThreadLocal<>();
	
	/* Internationalisation Resource Bundle for Strings */
	private ResourceBundle i18nBundle;
	
	private ContextHelp helpBubble;
	
	Window mainWindow;
	
	public static final String APP_VERSION = "0.0.1";
	
	/* Constructor */
	public AccountsApplication() {
		sessionInstance.set(this);
		
		helpBubble = new ContextHelp();
	}	
	
	@Override
	public void init() {
		setTheme("accounts");
		
		i18nBundle = ResourceBundle.getBundle(
				AccountsMessages.class.getName(), getLocale());
		mainWindow = new Window(i18nBundle.getString(
				AccountsMessages.APP_TITLE));
		setMainWindow(mainWindow);
		
		DisplayManager.getDisplayManager()
			.loadNewView(new AccountsLoginView());
	}
	
	public static void setCurrentView(IAccountsView view) {
		sessionInstance.get().mainWindow.setContent(view);
	}
	
	public static ResourceBundle getResourceBundle() {
		return sessionInstance.get().i18nBundle;
	}
	
	public static ContextHelp getHelpBubble() {
        return sessionInstance.get().helpBubble;
    }
	
	public static AccountsApplication getInstance() {
		return sessionInstance.get();
	}

	@Override
	public void setLocale(Locale locale) {
		super.setLocale(locale);
		i18nBundle = ResourceBundle.getBundle(
				AccountsMessages.class.getName(), getLocale());
		DisplayManager.getDisplayManager().refreshViews();
	}
	
	@Override
	public void onRequestStart(HttpServletRequest request,
			HttpServletResponse response) {
		sessionInstance.set(this);	
	}


	@Override
	public void onRequestEnd(HttpServletRequest request,
			HttpServletResponse response) {
		sessionInstance.remove();
	}
}