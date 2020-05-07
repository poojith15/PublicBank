package com.cg.banking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.banking.dao.BankDao;
import com.cg.banking.dto.CustomerForm;
import com.cg.banking.entity.Branch;
import com.cg.banking.entity.Customer;
import com.cg.banking.exceptions.CustomerException;
import com.cg.banking.util.AccountConstants;

/************************************************************************************
 *          @author           Sreeram
 *          Description       It is a service class that provides the services for
 *                                     Customer Management.
 *          @version          1.0
 *          Created Date      10-APR-2020
************************************************************************************/

@Service("customerser")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private BankDao dao;

	/***********************************************************************************************
	 * Method              :addCustomer
     * Description         :To add the Customer
	 * @param custForm     :CustomerForm instance
	 * @returns String     :Appropriate Message or it throws CustomerException  
     * Created By          :Sreeram
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	@Override
	public String addCustomer(CustomerForm custForm) {
		Customer cust = new Customer();
		Branch branch = dao.viewBranch(custForm.getBranchCode());
		LocalDateTime now = LocalDateTime.now();
		String custID = AccountConstants.EMPTY + now.getYear() + now.getMonthValue() + now.getDayOfMonth()
				+ now.getHour() + now.getMinute() + now.getSecond();
		cust.setCustomerId(custID);
		cust.setCustomerName(custForm.getCustomerName());
		cust.setCustomerDob(custForm.getCustomerDob());
		cust.setCustomerAadhar(custForm.getCustomerAadhar());
		cust.setCustomerAddress(custForm.getCustomerAddress());
		cust.setCustomerContact(custForm.getCustomerContact());
		cust.setCustomerPan(custForm.getCustomerPan());
		cust.setPassword(custForm.getPassword());
		cust.setRole(AccountConstants.ROLE_USER);
		cust.setCustomerGender(custForm.getCustomerGender());
		cust.setBranch(branch);
		dao.addCustomer(cust);
		return custID;
	}

	/***********************************************************************************************
	 * Method              :editCustomer
     * Description         :To edit the Customer
	 * @param cust         :Customer instance
	 * @returns Boolean    :Appropriate Message or it throws CustomerException  
     * Created By          :Sreeram
     * Created Date        :11-APR-2020                          
    ************************************************************************************************/
	@Override
	public boolean editCustomer(Customer cust) {
		return dao.editCustomer(cust);
	}

	/***********************************************************************************************
	 * Method              :viewCustomer
     * Description         :To get the Customer
	 * @param custId       :Customer ID(String)
	 * @returns Customer   :instance, if not throws LoginException  
	 * @throws CustomerException
	 * It is raised if customer is invalid.
     * Created By          :Sreeram
     * Created Date        :10-APR-2020                          
    ************************************************************************************************/
	@Override
	public Customer viewCustomer(String custId) throws CustomerException {
		Customer customer = dao.viewCustomer(custId);
		if (customer == null)
			throw new CustomerException(AccountConstants.INVALID_CUSTOMER);
		return customer;
	}

}
