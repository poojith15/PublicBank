package com.cg.banking.service;

import java.util.List;

import com.cg.banking.dto.LoanRequestForm;
import com.cg.banking.entity.LoanRequest;
import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.CustomerException;

/********************************************************************************************
 *          @author          Saineel
 *          Description      It is a service interface that contains method declarations
 *                                      which are implemented in LoanRequestServiceImpl.java
 *          @version         1.0
 *          Created Date     07-APR-2020
 ********************************************************************************************/

public interface LoanRequestService {

	public String loanRequest(LoanRequestForm req) throws CustomerException;

	public LoanRequest getLoanRequestStatus(String loanReqId);

	public List<LoanRequest> getLoanRequest(String customerId) throws AccountException;

	public List<LoanRequest> getLoanRequestNew() throws AccountException;

	public boolean processLoan(String loanReqId) throws CustomerException;

}
