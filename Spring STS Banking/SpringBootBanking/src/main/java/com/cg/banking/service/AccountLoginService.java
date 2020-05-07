package com.cg.banking.service;

import com.cg.banking.entity.Customer;
import com.cg.banking.exceptions.LoginException;

/********************************************************************************************
 *          @author          Poojith
 *          Description      It is a service interface that contains method declarations
 *                                      which are implemented in AccountLoginServiceImpl.java
 *          @version         1.0
 *          Created Date     07-APR-2020
 ********************************************************************************************/

public interface AccountLoginService {
	public Customer doLogin(String userId, String password) throws LoginException;

	public String encryptUser(Customer user);

}
