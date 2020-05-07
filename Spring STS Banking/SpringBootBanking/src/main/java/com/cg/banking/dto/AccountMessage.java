package com.cg.banking.dto;

/**********************************************************************************************************
 *          @author          Sreeram
 *          Description      It is a DTO(Data Transfer Object) class of AccountMessage that is used to send
 *                                      the data from Spring Boot to Angular and vice versa.
 *          @version         1.0
 *          Created Date     17-APR-2020
 **********************************************************************************************************/

public class AccountMessage {

	private String message;

	public AccountMessage(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
