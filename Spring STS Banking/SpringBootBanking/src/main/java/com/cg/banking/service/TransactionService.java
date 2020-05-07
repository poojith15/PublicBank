package com.cg.banking.service;

import java.time.LocalDate;
import java.util.List;

import com.cg.banking.entity.AccTransaction;
import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.BalanceException;
import com.cg.banking.exceptions.TransactionException;

/********************************************************************************************
 *          @author          Poojith
 *          Description      It is a service interface that contains method declarations
 *                                      which are implemented in TransactionServiceImpl.java
 *          @version         1.0
 *          Created Date     07-APR-2020
 ********************************************************************************************/

public interface TransactionService {
	public boolean transferFund(String from, String to, double amount)
			throws BalanceException, AccountException, TransactionException;

	public List<AccTransaction> getTransactions(String accId) throws TransactionException, AccountException;

	public List<AccTransaction> getTransactions(String accId, LocalDate fromdt, LocalDate todt)
			throws TransactionException, AccountException;
}
