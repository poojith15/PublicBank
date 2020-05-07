package com.cg.banking.dto;

/**********************************************************************************************************
 *          @author          Sreeram
 *          Description      It is a DTO(Data Transfer Object) class of TransferFundForm that is used to send
 *                                      the data from Spring Boot to Angular and vice versa.
 *          @version         1.0
 *          Created Date     17-APR-2020
 **********************************************************************************************************/

public class TransferFundForm {

	private String fromAccountId;
	private String toAccountId;
	private double amt;

	public String getFromAccountId() {
		return fromAccountId;
	}

	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}

	public String getToAccountId() {
		return toAccountId;
	}

	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

}
