package com.mooneyserver.account.i18n;

import java.io.Serializable;
import java.util.ListResourceBundle;

public class AccountsMessages extends ListResourceBundle implements Serializable {
	
	private static final long serialVersionUID = 1L;


	/*
	 * Main Windows Strings
	 */
	public static final String APP_TITLE = "Accounts.MainWindow.AppTitle";
	public static final String TOOLBAR_LANG_BTN_TOOLTIP = "Accounts.MainWindow.LangChange.Tooltip";
	
	
	/*
	 * Login Window String
	 */
	public static final String LOGIN_BUTTON = "Accounts.LoginView.LoginBtn";
	public static final String LOGIN_WELCOME = "Accounts.LoginView.Welcome";
	public static final String PASSWORD = "Accounts.LoginView.Password";
	public static final String FORGOT_PWD = "Accounts.LoginView.ForgotPassword";
	public static final String CREATE_NEW_USER = "Accounts.LoginView.CreateUser";
	public static final String EMAIL_ADDRESS = "Accounts.LoginView.ForgotPwd.Email";
	public static final String SEND_NEW_PASSWORD = "Accounts.LoginView.ForgotPwd.SendBtn";
	public static final String FIRST_NAME = "Accounts.LoginView.CreateUser.FirstName";
	public static final String LAST_NAME = "Accounts.LoginView.CreateUser.LastName";
	public static final String CONFIRM_PASSWORD = "Accounts.LoginView.CreateUser.ConfirmPassword";
	public static final String PASSWORD_REQUIREMENTS = "Accounts.LoginView.CreateUser.PasswordRequirements";
	public static final String DUPLICATE_USERNAME = "Accounts.LoginView.CreateUser.Error.DuplicateUser";
	public static final String INVALID_PASSWORD = "Accounts.LoginView.CreateUser.Error.DuplicateUser";
	
	/*
	 * Language Settings sub window
	 */
	public static final String SELECT_LANGUAGE = "Accounts.Settings.Language.ChooseLanguage";
	public static final String NEXT_LANGUAGE_UP = "Accounts.Settings.Language.NextUp";
	public static final String NEXT_LANGUAGE_DOWN = "Accounts.Settings.Language.NextDown";
	
	/*
	 * About Dialog Strings
	 */
	public static final String ABOUT_MAIN_HEADER = "Accounts.About.Header";
	public static final String ABOUT_MAIN = "Accounts.About.MainText";
	public static final String ABOUT_LINK = "Accounts.About.LinkCaption";
	
	/*
	 * Header Toolbar
	 */
	public static final String HEADER_SETTINGS = "Accounts.Header.Settings";
	public static final String HEADER_SETTINGS_LANG = "Accounts.Header.Settings.ChangeLanguage";
	public static final String HEADER_SETTINGS_SIGNOUT = "Accounts.Header.Settings.SignOut";
	
	/*
	 * Validation Localization
	 */
	public static final String VALIDATE_EMAIL = "Validate.Email";
	
	/*
	 * Messenger
	 */
	public static final String MSGR_TITLE_INFO = "Messenger.Title.Info";
	public static final String MSGR_TITLE_WARN = "Messenger.Title.Warning";
	public static final String MSGR_TITLE_ERROR = "Messenger.Title.Error";
	
	
	@Override
	protected Object[][] getContents() {
		return null;
	}
}