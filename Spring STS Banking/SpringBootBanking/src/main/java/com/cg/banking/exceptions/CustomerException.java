package com.cg.banking.exceptions;

/****************************************************************************************
 *          @author          Ganesh
 *          Description      It is a customized Exception class of CustomerException.
 *          @version         1.0
 *          Created Date     10-APR-2020
*****************************************************************************************/

public class CustomerException extends Exception {

	public CustomerException() {
		super();
	}

	public CustomerException(String message) {
		super(message);
	}

}
