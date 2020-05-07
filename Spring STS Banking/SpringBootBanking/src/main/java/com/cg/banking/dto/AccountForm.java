package com.cg.banking.dto;

/********************************************************************************************************
 *          @author          Sreeram
 *          Description      It is a DTO(Data Transfer Object) class of AccountForm that is used to send
 *                                      the data from Spring Boot to Angular and vice versa.
 *          @version         1.0
 *          Created Date     17-APR-2020
 ********************************************************************************************************/

public class AccountForm {

	private String accountName;
	private double balance;
	private String customerID;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getCustomerID() {
		return customerID;
	}

	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

}
