package com.cg.banking.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.banking.dao.BankDao;
import com.cg.banking.dto.AccountForm;
import com.cg.banking.dto.LoanRequestForm;
import com.cg.banking.entity.Customer;
import com.cg.banking.entity.LoanRequest;
import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.CustomerException;
import com.cg.banking.exceptions.LoanRequestException;
import com.cg.banking.util.AccountConstants;

/************************************************************************************
 *          @author           Saineel
 *          Description       It is a service class that provides the services for
 *                                     Loan Management.
 *          @version          1.0
 *          Created Date      10-APR-2020
************************************************************************************/

@Service("loanreqser")
@Transactional
public class LoanRequestServiceImpl implements LoanRequestService {

	@Autowired
	private BankDao dao;

	@Autowired
	private AccountService accountService;

	/***********************************************************************************************
	 * Method              :loanRequest
     * Description         :To add the loan request
	 * @param req          :LoanRequestForm instance
	 * @returns String     :Appropriate message if not throws CustomerException  
	 * @throws CustomerException
	 * It is raised if customer is not present.
     * Created By          :Saineel
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public String loanRequest(LoanRequestForm req) throws CustomerException {
		Customer cust = dao.viewCustomer(req.getCustomerId());
		if (cust == null)
			throw new CustomerException(AccountConstants.INVALID_CUSTOMER);
		long var = dao.countLoansForCustomer(req.getCustomerId()) + 1;
		String loanReqId = req.getCustomerId() + AccountConstants.EMPTY + var;
		LoanRequest loanReq = new LoanRequest();
		loanReq.setLoanRequestId(loanReqId);
		loanReq.setLoanAmount(req.getLoanAmt());
		loanReq.setLoanTenure(req.getTenure());
		loanReq.setLoanType(req.getLoanType());
		loanReq.setCustomer(cust);
		loanReq.setReqStatus(AccountConstants.LOAN_REQUEST);
		loanReq.setDateOfRequest(LocalDate.now());
		loanReq.setAnnualIncome(req.getAnnualIncome());
		dao.loanRequest(loanReq);
		return loanReqId;
	}

	/***********************************************************************************************
	 * Method              :getLoanRequestStatus
     * Description         :To get the loan request
	 * @param loanReqId    :LoanRequest ID(String)
	 * @returns LoanRequest:instance  
     * Created By          :Saineel
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	@Override
	public LoanRequest getLoanRequestStatus(String loanReqId) {
		return dao.getLoanRequestStatus(loanReqId);
	}

	/***********************************************************************************************
	 * Method              :getLoanRequest
     * Description         :To get the list of loan request
	 * @param customerId   :customer ID(String)
	 * @returns List       :list of LoanRequest  
	 * @throws AccountException
	 * It is raised if there are no loan requests.
     * Created By          :Saineel
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	@Override
	public List<LoanRequest> getLoanRequest(String customerId) throws AccountException {
		List<LoanRequest> loanReqList = dao.getLoanRequest(customerId);
		if (loanReqList.isEmpty())
			throw new AccountException(AccountConstants.NO_LOAN);
		loanReqList.sort((l1, l2) -> l2.getDateOfRequest().compareTo(l1.getDateOfRequest()));
		return loanReqList;
	}

	/***********************************************************************************************
	 * Method              :getLoanRequestNew
     * Description         :To get the list of new loan request.
	 * @returns List       :List of new loan request if not throws CustomerException  
	 * @throws AccountException
	 * It is raised if there is no loan request.
     * Created By          :Saineel
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	@Override
	public List<LoanRequest> getLoanRequestNew() throws AccountException {
		List<LoanRequest> loanReqList = dao.getLoanRequestByStatus(AccountConstants.LOAN_REQUEST);
		if (loanReqList.isEmpty())
			throw new AccountException(AccountConstants.NO_LOAN);
		loanReqList.sort((l1, l2) -> l1.getDateOfRequest().compareTo(l2.getDateOfRequest()));
		return loanReqList;

	}

	/***********************************************************************************************
	 * Method              :processLoan
     * Description         :To proccess the loan request
	 * @param loanReqId    :LoanRequest ID(String)
	 * @returns Boolean    :true,if request is approved and false if request is rejected
	 *                                        if not throws CustomerException  
	 * @throws CustomerException
	 * It is raised if customer is not present.
     * Created By          :Saineel
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Override
	public boolean processLoan(String loanReqId) throws CustomerException   {
		LoanRequest req = dao.getLoanRequestStatus(loanReqId);
		Customer cust = req.getCustomer();
		if (cust == null)
			throw new CustomerException(AccountConstants.INVALID_CUSTOMER);
		long var = dao.checkExistsLoanForCustomer(cust.getCustomerId());
		if (var > AccountConstants.LOAN_EXISTS) {
			req.setReqStatus(AccountConstants.LOAN_REJECTED);
			dao.editLoanRequest(req);
			return false;
		}
		double compoundInt = calcCompoundInt(req.getLoanAmount(), req.getLoanTenure());
		double emi = compoundInt / (req.getLoanTenure() * AccountConstants.TWELVE_MONTHS);
		if (emi > (req.getAnnualIncome() / AccountConstants.TWELVE_MONTHS) * AccountConstants.FIFTY_PERCENT) {
			req.setReqStatus(AccountConstants.LOAN_REJECTED);
			dao.editLoanRequest(req);
			return false;
		}
		req.setReqStatus(AccountConstants.LOAN_APPROVED);
		dao.editLoanRequest(req);
		AccountForm form = new AccountForm();
		form.setAccountName(AccountConstants.LOAN);
		form.setBalance(req.getLoanAmount());
		form.setCustomerID(req.getCustomer().getCustomerId());

		accountService.addAccount(form);
		return true;
	}

	/***********************************************************************************************
	 * Method              :calcCompoundInt
     * Description         :To calculate the compound interest
	 * @param loanAmt      :Loan amount(double)
	 * @param tenure       :Years(int)
	 * @returns double     :compound interest 
     * Created By          :Saineel
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	public double calcCompoundInt(double loanAmt, int tenure) {
		return loanAmt * Math.pow((AccountConstants.ONE + AccountConstants.LOAN_RATE), tenure);
	}
}
