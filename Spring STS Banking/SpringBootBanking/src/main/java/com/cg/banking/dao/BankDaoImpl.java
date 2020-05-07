package com.cg.banking.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.banking.entity.AccTransaction;
import com.cg.banking.entity.Account;
import com.cg.banking.entity.Branch;
import com.cg.banking.entity.Customer;
import com.cg.banking.entity.LoanRequest;

/****************************************************************************************
 *          @author          Saineel
 *          Description      It is a DAO implementation class that interacts with
 *                                      Oracle DataBase for Account,Customer,Transaction
 *                                      and Loan Management.
 *          @version         1.0
 *          Created Date     10-APR-2020
*****************************************************************************************/

@Repository
public class BankDaoImpl implements BankDao {

	@PersistenceContext
	private EntityManager em;
	
	/************************************************************************************
	 * Method              :addCustomer
     * Description         :To add customer in the Oracle Data base
	 * @param cust         :Customer instance
	 * @returns Boolean    :true, Customer instance is added to the data base 
     * Created By          :Sreeram
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public boolean addCustomer(Customer cust) {
		em.persist(cust);
		return true;
	}

	/************************************************************************************
	 * Method              :editCustomer
     * Description         :To edit customer in the Oracle Data base
	 * @param cust         :Customer instance
	 * @returns Boolean    :true, Customer instance is edited to the data base 
     * Created By          :Sreeram
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public boolean editCustomer(Customer cust) {
		em.merge(cust);
		return true;
	}

	/************************************************************************************
	 * Method              :viewCustomer
     * Description         :To view customer from the Oracle Data base
	 * @param custId       :Customer ID
	 * @returns Customer   :instance
     * Created By          :Sreeram
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public Customer viewCustomer(String custId) {

		return em.find(Customer.class, custId);
	}

	/************************************************************************************
	 * Method              :addAccount
     * Description         :To add Account in the Oracle Data base
	 * @param account      :Account instance
	 * @returns Boolean    :true, Account instance is added to the data base 
     * Created By          :Ganesh
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public boolean addAccount(Account account) {
		em.persist(account);
		return true;
	}

	/************************************************************************************
	 * Method              :editAccount
     * Description         :To edit Account in the Oracle Data base
	 * @param account      :Account instance
	 * @returns Boolean    :true, Account instance is edited to the data base 
     * Created By          :Ganesh
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public boolean editAccount(Account account) {
		em.merge(account);
		return true;
	}

	/************************************************************************************
	 * Method              :viewAccount
     * Description         :To view Accounts from the Oracle Data base
	 * @param accId        :Account ID
	 * @returns Account    :instance 
     * Created By          :Ganesh
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public Account viewAccount(String accId) {

		return em.find(Account.class, accId);
	}

	/************************************************************************************
	 * Method              :viewAccounts
     * Description         :To view Accounts from the Oracle Data base
	 * @param customerId   :Customer ID
	 * @returns List       :List of Accounts 
     * Created By          :Ganesh
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public List<Account> viewAccounts(String customerId) {
		String jpql = "from Account a inner join fetch a.customer c where c.customerId=:custid";
		TypedQuery<Account> query = em.createQuery(jpql, Account.class);
		query.setParameter("custid", customerId);
		return query.getResultList();

	}

	/************************************************************************************
	 * Method              :addTxn
     * Description         :To add AccTransaction in the Oracle Data base
	 * @param tx           :AccTransaction instance
	 * @returns Boolean    :true, AccTransaction instance is added to the data base 
     * Created By          :Poojith
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public boolean addTxn(AccTransaction tx) {
		em.persist(tx);
		return true;
	}

	/************************************************************************************
	 * Method              :getTransactions
     * Description         :To get transactions from the Oracle Data base of certain number.
	 * @param accId        :Account ID
	 * @param no           :integer
	 * @returns List       :List of AccTransaction
     * Created By          :Poojith
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public List<AccTransaction> getTransactions(String accId, int no) {
		String jpql = "from AccTransaction tx inner join fetch tx.account a inner join fetch a.customer c where a.accountId=:accid order by tx.transaccountId desc";
		TypedQuery<AccTransaction> query = em.createQuery(jpql, AccTransaction.class);
		query.setParameter("accid", accId);
		query.setFirstResult(0);
		query.setMaxResults(no);
		return query.getResultList();
	}

	/************************************************************************************
	 * Method              :getTransactions
     * Description         :To get transactions from the Oracle Data base in the range of dates.
	 * @param accId        :Account ID
	 * @param fromdt       :From Date
	 * @param todt         :To Date 
	 * @returns List       :List of AccTransaction
     * Created By          :Poojith
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public List<AccTransaction> getTransactions(String accId, LocalDate fromdt, LocalDate todt) {
		String jpql = "from AccTransaction tx inner join fetch tx.account a inner join fetch a.customer c where a.accountId=:accid and tx.transDate between :from and :to order by  tx.transaccountId desc";
		TypedQuery<AccTransaction> query = em.createQuery(jpql, AccTransaction.class);
		query.setParameter("accid", accId);
		query.setParameter("from", fromdt);
		query.setParameter("to", todt);
		return query.getResultList();
	}

	/************************************************************************************
	 * Method              :loanRequest
     * Description         :To add loan request in the Oracle Data base
	 * @param req          :Loan Request instance
	 * @returns Boolean    :true, Loan Request added to the data base
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public boolean loanRequest(LoanRequest req) {
		em.persist(req);
		return true;
	}

	/************************************************************************************
	 * Method              :getLoanRequestStatus
     * Description         :To get loan request from the Oracle Data base
	 * @param loanReqId    :Loan Request ID
	 * @returns LoanRequest:instance
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public LoanRequest getLoanRequestStatus(String loanReqId) {

		return em.find(LoanRequest.class, loanReqId);
	}

	/************************************************************************************
	 * Method              :getLoanRequest
     * Description         :To get loan request by custmoer ID from the Oracle Data base
	 * @param customerId   :customer ID
	 * @returns List       :List of LoanRequest
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public List<LoanRequest> getLoanRequest(String customerId) {
		String jpql = "from LoanRequest lr inner join fetch lr.customer c where c.customerId=:custid";
		TypedQuery<LoanRequest> query = em.createQuery(jpql, LoanRequest.class);
		query.setParameter("custid", customerId);
		return query.getResultList();
	}

	/************************************************************************************
	 * Method              :viewBranch
     * Description         :To view branch from the Oracle Data base
	 * @param branchCode   :branch ID
	 * @returns Branch     :instance
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public Branch viewBranch(String branchCode) {

		return em.find(Branch.class, branchCode);
	}

	/************************************************************************************
	 * Method              :getLoanRequestByStatus
     * Description         :To view list of loan request from the Oracle Data base
	 * @param reqStatus    :Loan Request status
	 * @returns List       :LoanRequest
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public List<LoanRequest> getLoanRequestByStatus(String reqStatus) {
		String jpql = "from LoanRequest lr inner join fetch lr.customer c where lr.reqStatus=:reqstatus";
		TypedQuery<LoanRequest> query = em.createQuery(jpql, LoanRequest.class);
		query.setParameter("reqstatus", reqStatus);
		return query.getResultList();
	}

	/************************************************************************************
	 * Method              :countLoansForCustomer
     * Description         :To get the number of loanRequestId for the given customer Id
     *                                          from the Oracle Data base
	 * @param custID       :Loan Request instance
	 * @returns long       :number of loanRequestId
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public long countLoansForCustomer(String custID) {
		String jpql = "select count(r.loanRequestId) from LoanRequest r inner join r.customer c where c.customerId=:custId";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("custId", custID);
		return query.getSingleResult();
	}

	/************************************************************************************
	 * Method              :checkExistsLoanForCustomer
     * Description         :To get the number of accountId for the given customer Id
     *                                           from the Oracle Data base
	 * @param custId       :Loan Request instance
	 * @returns long       :number of accountId
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public long checkExistsLoanForCustomer(String custId) {
		String jpql = "select count(a.accountId) from Account a inner join a.customer c where c.customerId=:custId and a.accountName like :loan";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("custId", custId);
		query.setParameter("loan", "%LOAN%");
		return query.getSingleResult();
	}

	/************************************************************************************
	 * Method              :editLoanRequest
     * Description         :To edit loan request in theOracle Data base
	 * @param req          :Loan Request instance
	 * @returns Boolean    :true, Loan Request edited to the data base
     * Created By          :Saineel
     * Created Date        :09-APR-2020                           
    **************************************************************************************/
	@Override
	public boolean editLoanRequest(LoanRequest req) {
		em.merge(req);
		return true;
	}

}
