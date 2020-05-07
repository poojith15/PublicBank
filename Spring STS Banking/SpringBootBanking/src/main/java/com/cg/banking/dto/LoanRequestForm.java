package com.cg.banking.dto;

/**********************************************************************************************************
 *          @author          Sreeram
 *          Description      It is a DTO(Data Transfer Object) class of LoanRequestForm that is used to send
 *                                      the data from Spring Boot to Angular and vice versa.
 *          @version         1.0
 *          Created Date     17-APR-2020
 **********************************************************************************************************/

public class LoanRequestForm {

	private double loanAmt;
	private int tenure;
	private String customerId;
	private String loanType;
	private double annualIncome;

	public double getLoanAmt() {
		return loanAmt;
	}

	public void setLoanAmt(double loanAmt) {
		this.loanAmt = loanAmt;
	}

	public int getTenure() {
		return tenure;
	}

	public void setTenure(int tenure) {
		this.tenure = tenure;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public double getAnnualIncome() {
		return annualIncome;
	}

	public void setAnnualIncome(double annualIncome) {
		this.annualIncome = annualIncome;
	}

}
