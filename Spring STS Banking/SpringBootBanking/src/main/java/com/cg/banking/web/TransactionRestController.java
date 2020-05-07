package com.cg.banking.web;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.BalanceException;
import com.cg.banking.exceptions.CustomerException;
import com.cg.banking.exceptions.TransactionException;
import com.cg.banking.service.TransactionService;
import com.cg.banking.util.AccountConstants;
import com.cg.banking.dto.AccReportForm;
import com.cg.banking.dto.TransferFundForm;
import com.cg.banking.entity.AccTransaction;

/**************************************************************************************
 *          @author           Poojith
 *          Description       It is a Rest Controller class that provides the suitable
 *                                       Transaction Management methods for the given 
 *                                       matching URL and returns response in different
 *                                       types of data objects.
 *          @version          1.0
 *          Created Date      12-APR-2020
***************************************************************************************/

@RestController
public class TransactionRestController {

	@Autowired
	private TransactionService ser;

	/***********************************************************************************************************
	 * Method                 :viewTransaction
     * Description            :To provide the service of viewing transactions for the "/getTxn/{accountId}" URL.
	 * @param accId           :Account ID
	 * @param request         :HttpServletRequest instance
	 * @returns List          :List of Transactions if not throws LoginException,
	 *                                    AccountException,TransactionException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if account is already exists.
	 * @throws TransactionException
	 * It is raised if account is inactive.
     * Created By          :Poojith
     * Created Date        :13-APR-2020                          
    ************************************************************************************************************/
	@CrossOrigin
	@GetMapping("/getTxn/{accountId}")
	public List<AccTransaction> viewTransaction(@PathVariable("accountId") String accId, HttpServletRequest request)
			throws TransactionException, AccountException, LoginException {
		//if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			List<AccTransaction> txnList = ser.getTransactions(accId);
			return txnList;
//		}
//		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

	/***********************************************************************************************************
	 * Method                 :viewTransaction
     * Description            :To provide the service of viewing transactions for the "/getTxn" URL.
	 * @param form            :AccReportForm
	 * @param request         :HttpServletRequest instance
	 * @returns List          :List of Transactions if not throws LoginException,
	 *                                    AccountException,TransactionException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if account is already exists.
	 * @throws TransactionException
	 * It is raised if account is inactive.
     * Created By          :Poojith
     * Created Date        :13-APR-2020                          
    ************************************************************************************************************/
	@CrossOrigin
	@PostMapping("/getTxn")
	public List<AccTransaction> viewTransaction(@RequestBody AccReportForm form, HttpServletRequest request)
			throws TransactionException, AccountException, LoginException {
		if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			List<AccTransaction> txnList = ser.getTransactions(form.getAccountId(), form.getFromDate(),
					form.getToDate());
			return txnList;
		}
		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

	/***********************************************************************************************************
	 * Method                 :doTransaction
     * Description            :To provide the service of performing transactions for the "/doTxn" URL.
	 * @param form            :TransferFundForm instance
	 * @param request         :HttpServletRequest instance
	 * @returns List          :List of Transactions if not throws LoginException,
	 *                                    AccountException,BalanceException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if account is already exists.
	 * @throws BalanceException
	 * It is raised if balance is insufficient.
     * Created By          :Poojith
     * Created Date        :13-APR-2020                          
    ************************************************************************************************************/
	@CrossOrigin
	@PostMapping("/doTxn")
	public String doTransaction(@RequestBody TransferFundForm form, HttpServletRequest request)
			throws BalanceException, AccountException {
		try {
			if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
				ser.transferFund(form.getFromAccountId(), form.getToAccountId(), form.getAmt());
				return "Transaction is completed";
			}
			throw new LoginException(AccountConstants.PLEASE_LOGIN);
		} catch (DataIntegrityViolationException ex) {
			throw new BalanceException("Insufficient Balance");
		} catch (Exception ex) {
			throw new AccountException("Account does not exist");
		}

	}
}
