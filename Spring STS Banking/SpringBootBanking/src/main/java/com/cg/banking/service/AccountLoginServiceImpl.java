package com.cg.banking.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.banking.dao.BankDao;
import com.cg.banking.entity.Customer;
import com.cg.banking.exceptions.LoginException;
/************************************************************************************
 *          @author           Poojith
 *          Description       It is a service class that provides the services for
 *                                     Login and Logout.
 *          @version          1.0
 *          Created Date      10-APR-2020
************************************************************************************/

@Service
@Transactional
public class AccountLoginServiceImpl implements AccountLoginService {

	@Autowired
	private BankDao dao;

	Logger logger = LoggerFactory.getLogger(AccountLoginServiceImpl.class);

	/***********************************************************************************************
	 * Method              :doLogin
     * Description         :checks the user ID and password
	 * @param userId       :User ID(String)
	 * @param password     :password(String)
	 * @returns Customer   :instance, if userId and password is correct if not throws LoginException  
	 * @throws AccountException
	 * It is raised due to incorrect values of userId and password.
     * Created By          :Poojith
     * Created Date        :10-APR-2020                          
    ************************************************************************************************/
	@Override
	public Customer doLogin(String userId, String password) throws LoginException {
		Customer user = dao.viewCustomer(userId);
		logger.debug("doing login process");

		if (user != null && user.getPassword().contentEquals(password)) {
			logger.info("User Authenticated for " + userId);
			return user;
		}
		throw new LoginException("You are not authenticated and authorized, Please Login");
	}

	/************************************************************************************
	 * Method              :encryptUser
     * Description         :To encrypt the customer
	 * @param user         :Customer instance
	 * @returns String     :encrypted string
     * Created By          :Poojith
     * Created Date        :10-APR-2020                           
    **************************************************************************************/
	@Override
	public String encryptUser(Customer user) {
		return encryptString(user.getCustomerId()) + "-" + encryptString(user.getCustomerName()) + "-"
				+ encryptString(user.getRole());
	}

	/************************************************************************************
	 * Method              :encryptString
     * Description         :To encrypt the String
	 * @param str          :String
	 * @returns String     :encrypted String
     * Created By          :Poojith
     * Created Date        :10-APR-2020                           
    **************************************************************************************/
	public String encryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch;
		for (int idx = 0; idx < arr.length; ++idx) {
			ch = arr[idx] + 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}

	/************************************************************************************
	 * Method              :decryptString
     * Description         :To decrypt the String
	 * @param str          :String
	 * @returns String     :decrypted String
     * Created By          :Poojith
     * Created Date        :10-APR-2020                           
    **************************************************************************************/
	public String decryptString(String str) {
		char[] arr = str.toCharArray();
		StringBuffer sb = new StringBuffer();
		int ch;
		for (int idx = 0; idx < arr.length; ++idx) {
			ch = arr[idx] - 3;
			sb.append((char) ch);
		}
		return sb.toString();
	}
}
