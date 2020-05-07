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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.BalanceException;
import com.cg.banking.exceptions.CustomerException;
import com.cg.banking.exceptions.TransactionException;
import com.cg.banking.service.AccountService;
import com.cg.banking.util.AccountConstants;
import com.cg.banking.dto.AccountForm;
import com.cg.banking.dto.AccountMessage;
import com.cg.banking.entity.Account;
/**************************************************************************************
 *          @author           Ganesh
 *          Description       It is a Rest Controller class that provides the suitable
 *                                       Account Management methods for the given 
 *                                       matching URL and returns response in different
 *                                       types of data objects.
 *          @version          1.0
 *          Created Date      12-APR-2020
***************************************************************************************/
@RestController
public class AccountRestController {

	@Autowired
	public AccountService service;

	/***********************************************************************************************
	 * Method                 :addAccount
     * Description            :To provide the service of adding account for the "/addaccount" URL.
	 * @param accountForm     :AccountForm instance
	 * @param request         :HttpServletRequest instance
	 * @returns AccountMessage:Appropriate Message if not throws LoginException,
	 *                                    AccountException,CustomerException
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if account is already exists.
	 * @throws CustomerException
	 * It is raised account is inactive.
     * Created By          :Ganesh
     * Created Date        :13-APR-2020                          
    ************************************************************************************************/
	@CrossOrigin
	@PostMapping("/addaccount")
	public AccountMessage addAccount(@RequestBody AccountForm accountForm, HttpServletRequest request)
			throws AccountException, CustomerException, LoginException {
		try {
			if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
				String accId = service.addAccount(accountForm);
				return new AccountMessage(
						AccountConstants.ACCOUNT_CREATED + AccountConstants.GENERATED_ACCOUNT + accId);
			}
			throw new LoginException(AccountConstants.PLEASE_LOGIN);
		} catch (DataIntegrityViolationException ex) {
			throw new AccountException(AccountConstants.ID_ALREADY_EXISTS);
		}
	}

	/***********************************************************************************************
	 * Method                 :editAccount
     * Description            :To provide the service of editing account for the "/editaccount" URL.
	 * @param account     :Account instance
	 * @param request         :HttpServletRequest instance
	 * @returns AccountMessage:Appropriate Message if not throws LoginException.
	 * @throws LoginException
	 * It is raised if user does not login.
     * Created By          :Ganesh
     * Created Date        :13-APR-2020                          
    ************************************************************************************************/
	@CrossOrigin
	@PutMapping("/editaccount")
	public AccountMessage editAccount(@RequestBody Account account, HttpServletRequest request) throws LoginException {
		if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			service.editAccount(account);
			AccountMessage msg = new AccountMessage(AccountConstants.ACCOUNT_EDITED);
			return msg;
		}
		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

	/**************************************************************************************************************
	 * Method                 :viewAccount
     * Description            :To provide the service of viewing account for the "/viewaccountbyaccid/{accid}" URL.
	 * @param accId           :Account ID(String)
	 * @param request         :HttpServletRequest instance
	 * @returns Account       :instance if not throws LoginException,
	 *                                    AccountException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws AccountException
	 * It is raised if account is already exists.
     * Created By          :Ganesh
     * Created Date        :13-APR-2020                          
    ***************************************************************************************************************/
	@CrossOrigin
	@GetMapping("/viewaccountbyaccid/{accid}")
	public Account viewAccount(@PathVariable("accid") String accId, HttpServletRequest request)
			throws AccountException, LoginException {
	      if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			Account account = service.viewAccount(accId);
			return account;
		  }
	      throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

	/******************************************************************************************************************
	 * Method                 :viewAccounts
     * Description            :To provide the service of viewing accounts for the "/viewaccountsbycustid/{custid}" URL.
	 * @param accountForm     :AccountForm instance
	 * @param request         :HttpServletRequest instance
	 * @returns List          :List of accounts if not throws LoginException,
	 *                                    CustomerException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws CustomerException
	 * It is raised account is inactive.
     * Created By          :Ganesh
     * Created Date        :13-APR-2020                          
    *******************************************************************************************************************/
	@CrossOrigin
	@GetMapping("/viewaccountsbycustid/{custid}")
	public List<Account> viewAccounts(@PathVariable("custid") String custId, HttpServletRequest request)
			throws CustomerException, LoginException {
		if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			return service.viewAccounts(custId);
		}
		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}

}
