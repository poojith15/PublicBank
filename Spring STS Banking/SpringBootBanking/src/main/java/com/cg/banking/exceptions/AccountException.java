package com.cg.banking.exceptions;

/****************************************************************************************
 *          @author          Ganesh
 *          Description      It is a customized Exception class of AccountException.
 *          @version         1.0
 *          Created Date     10-APR-2020
*****************************************************************************************/
public class AccountException extends Exception {

	public AccountException() {
		super();
	}

	public AccountException(String message) {
		super(message);
	}

}
