package com.cg.banking.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

/**********************************************************************************************************
 *          @author          Sreeram
 *          Description      It is a DTO(Data Transfer Object) class of AccReportForm that is used to send
 *                                      the data from Spring Boot to Angular and vice versa.
 *          @version         1.0
 *          Created Date     17-APR-2020
 **********************************************************************************************************/

public class AccReportForm {

	private String accountId;
	@DateTimeFormat(pattern = "yyyy-M-d")
	private LocalDate fromDate;
	@DateTimeFormat(pattern = "yyyy-M-d")
	private LocalDate toDate;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public LocalDate getFromDate() {
		return fromDate;
	}

	public void setFromDate(LocalDate fromDate) {
		this.fromDate = fromDate;
	}

	public LocalDate getToDate() {
		return toDate;
	}

	public void setToDate(LocalDate toDate) {
		this.toDate = toDate;
	}

}
