package com.mooneyserver.account.businesslogic.exception.user;

import com.mooneyserver.account.businesslogic.exception.AccountsBaseException;

public class AccountsUserException extends AccountsBaseException {

	private static final long serialVersionUID = 1L;
	
	public AccountsUserException() {
		super();
	}
	
	public AccountsUserException(String msg) {
		super(msg);
	}
	
	public AccountsUserException(Throwable cause) {
		super(cause);
	}
	
	public AccountsUserException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
