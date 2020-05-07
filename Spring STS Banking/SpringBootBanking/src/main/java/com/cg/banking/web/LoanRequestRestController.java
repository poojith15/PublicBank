package com.cg.banking.web;

import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.CustomerException;
import com.cg.banking.exceptions.LoanRequestException;
import com.cg.banking.service.LoanRequestService;
import com.cg.banking.util.AccountConstants;
import com.cg.banking.dto.AccountMessage;
import com.cg.banking.dto.LoanRequestForm;
import com.cg.banking.entity.LoanRequest;

/**************************************************************************************
 *          @author           Saineel
 *          Description       It is a Rest Controller class that provides the suitable
 *                                       Loan Management methods for the given 
 *                                       matching URL and returns response in different
 *                                       types of data objects.
 *          @version          1.0
 *          Created Date      12-APR-2020
***************************************************************************************/

@RestController
public class LoanRequestRestController {

	@Autowired
	public LoanRequestService service;

	/******************************************************************************************************
	 * Method                 :addLoanRequest
     * Description            :To provide the service of adding loan request for the "/addloanrequest" URL.
	 * @param reqForm         :LoanRequestForm instance
	 * @param request         :HttpServletRequest instance
	 * @returns AccountMessage:Appropriate Message if not throws LoginException,
	 *                                    LoanRequestException,CustomerException
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws LoanRequestException
	 * It is raised if loan request ID is already exists.
	 * @throws CustomerException
	 * It is raised account is inactive.
     * Created By          :Saineel
     * Created Date        :13-APR-2020                          
    *****************************************************************************************************/
	@CrossOrigin
	@PostMapping("/addloanrequest")
	public AccountMessage addLoanRequest(@RequestBody LoanRequestForm reqForm, HttpServletRequest request)
			throws LoanRequestException, CustomerException, LoginException {
		try {
			if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
				String loanReqId = service.loanRequest(reqForm);
				return new AccountMessage(
						AccountConstants.LOAN_REQ_CREATED + AccountConstants.GENERATED_LOAN_REQ + loanReqId);
			}
			throw new LoginException(AccountConstants.PLEASE_LOGIN);
		} catch (DataIntegrityViolationException ex) {
			throw new LoanRequestException(AccountConstants.ID_ALREADY_EXISTS);
		}
	}
	/********************************************************************************************************************
	 * Method                 :viewLoanRequestStatus
     * Description            :To provide the service of viewing status for the "/viewloanrequeststatus/{loanreqid}" URL.
	 * @param loanReqId       :LoanRequest ID(String)
	 * @param request         :HttpServletRequest instance
	 * @returns LoanRequest   :instance if not throws LoginException,
	 *                                    LoanRequestException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws LoanRequestException
	 * It is raised if loan request not found.
     * Created By          :Saineel
     * Created Date        :13-APR-2020                          
    ************************************************************************************************************************/
	@CrossOrigin
	@GetMapping("/viewloanrequeststatus/{loanreqid}")
	public LoanRequest viewLoanRequestStatus(@PathVariable("loanreqid") String loanReqId, HttpServletRequest request)
			throws LoanRequestException, LoginException {
		if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			LoanRequest req = service.getLoanRequestStatus(loanReqId);
			if (req == null)
				throw new LoanRequestException("Loan Request Not Found");
			return req;
		}
		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

	/******************************************************************************************
	 * Method                 :viewLoanRequestByCustomer
     * Description            :To provide the service of viewing loan request by customer id
     *                                     for the "/viewloanrequestsbycustid/{custid}" URL.
	 * @param custId          :Customer ID(String)
	 * @param request         :HttpServletRequest instance
	 * @returns List          :List of Loan Requests, if not throws LoginException,
	 *                                    AccountException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if no loan account.
     * Created By          :Saineel
     * Created Date        :13-APR-2020                          
    ********************************************************************************************/
	@CrossOrigin
	@GetMapping("/viewloanrequestsbycustid/{custid}")
	public List<LoanRequest> viewLoanRequestByCustomer(@PathVariable("custid") String custId,
			HttpServletRequest request) throws AccountException, LoginException {
		if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			return service.getLoanRequest(custId);
		}
		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

	/******************************************************************************************
	 * Method                 :viewNewLoanRequest
     * Description            :To provide the service of viewing new loan requests
     *                                     for the "/viewloanrequestsbycustid/{custid}" URL.
	 * @param request         :HttpServletRequest instance
	 * @returns List          :List of Loan Requests, if not throws LoginException,
	 *                                    AccountException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if no loan account exists.
     * Created By          :Saineel
     * Created Date        :13-APR-2020                          
    ********************************************************************************************/
	@CrossOrigin
	@GetMapping("/viewnewloanrequests")
	public List<LoanRequest> viewNewLoanRequest(HttpServletRequest request) throws AccountException, LoginException {
		if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			return service.getLoanRequestNew();
		}
		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

	/*******************************************************************************************************************
	 * Method                 :processLoan
     * Description            :To provide the service of processing loan request for the "/processLoan/{loanreqid}" URL.
	 * @param loanReqId       :LoanRequest ID(String)
	 * @param request         :HttpServletRequest instance
	 * @returns AccountMessage:Appropriate Message if not throws LoginException,
	 *                                    AccountException,CustomerException
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if no loan account exists.
	 * @throws CustomerException
	 * It is raised account is inactive.
     * Created By          :Saineel
     * Created Date        :13-APR-2020                          
    *********************************************************************************************************************/
	@CrossOrigin
	@GetMapping("/processLoan/{loanreqid}")
	public AccountMessage processLoan(@PathVariable("loanreqid") String loanReqId, HttpServletRequest request)
			throws AccountException, CustomerException, LoginException {
		if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			service.processLoan(loanReqId);
			AccountMessage msg = new AccountMessage(AccountConstants.LOAN_PROCESSED);
			return msg;
		}
		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}
}
