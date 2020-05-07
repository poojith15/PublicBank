package com.cg.banking.service;

/********************************************************************************************
 *          @author          Sreeram
 *          Description      It is a service interface that contains method declarations
 *                                      which are implemented in CustomerServiceImpl.java
 *          @version         1.0
 *          Created Date     07-APR-2020
 ********************************************************************************************/

import com.cg.banking.dto.CustomerForm;
import com.cg.banking.entity.Customer;
import com.cg.banking.exceptions.CustomerException;

public interface CustomerService {

	public String addCustomer(CustomerForm custForm);

	public boolean editCustomer(Customer cust);

	public Customer viewCustomer(String custId) throws CustomerException;

}
