package com.cg.banking.service;

import java.util.List;

import com.cg.banking.dto.AccountForm;
import com.cg.banking.entity.Account;
import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.CustomerException;

/********************************************************************************************
 *          @author          Ganesh
 *          Description      It is a service interface that contains method declarations
 *                                      which are implemented in AccountServiceImpl.java
 *          @version         1.0
 *          Created Date     07-APR-2020
 ********************************************************************************************/

public interface AccountService {

	public String addAccount(AccountForm account) throws CustomerException;

	public boolean editAccount(Account account);

	public List<Account> viewAccounts(String custId) throws CustomerException;

	public Account viewAccount(String accId) throws AccountException;
}
