package com.cg.banking.exceptions;

/****************************************************************************************
 *          @author          Ganesh
 *          Description      It is a customized Exception class of LoanRequestException.
 *          @version         1.0
 *          Created Date     10-APR-2020
*****************************************************************************************/

public class LoanRequestException extends Exception {

	public LoanRequestException() {
		super();
	}

	public LoanRequestException(String message) {
		super(message);
	}

}
