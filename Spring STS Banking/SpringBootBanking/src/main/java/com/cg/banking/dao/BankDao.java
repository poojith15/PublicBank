package com.cg.banking.dao;

import java.time.LocalDate;
import java.util.List;

import com.cg.banking.entity.AccTransaction;
import com.cg.banking.entity.Account;
import com.cg.banking.entity.Branch;
import com.cg.banking.entity.Customer;
import com.cg.banking.entity.LoanRequest;

/**********************************************************************************************************
 *          @author          Saineel
 *          Description      It is a DAO(Data Access Object) interface of BankDao contains the method
 *                                      declarations of BankDaoImpl
 *          @version         1.0
 *          Created Date     10-APR-2020
 **********************************************************************************************************/

public interface BankDao {

	public boolean addCustomer(Customer cust);

	public boolean editCustomer(Customer cust);

	public Customer viewCustomer(String custId);

	public boolean addAccount(Account account);

	public boolean editAccount(Account account);

	public Account viewAccount(String accId);

	public List<Account> viewAccounts(String customerId);

	public long checkExistsLoanForCustomer(String custId);

	public Branch viewBranch(String branchCode);

	public boolean addTxn(AccTransaction tx);

	public List<AccTransaction> getTransactions(String accId, int no);

	public List<AccTransaction> getTransactions(String accId, LocalDate fromdt, LocalDate todt);

	public boolean loanRequest(LoanRequest req);

	public LoanRequest getLoanRequestStatus(String loanReqId);

	public List<LoanRequest> getLoanRequest(String customerId);

	public boolean editLoanRequest(LoanRequest req);

	public long countLoansForCustomer(String custID);

	public List<LoanRequest> getLoanRequestByStatus(String reqStatus);
}
