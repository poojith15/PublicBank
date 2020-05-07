package com.cg.banking.web;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.banking.entity.Customer;
import com.cg.banking.service.AccountLoginService;
import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.CustomerException;
import com.cg.banking.exceptions.LoginException;

/********************************************************************************************
 *          @author           Poojith
 *          Description       It is a Rest Controller class that provides the suitable
 *                                       Login methods for the given matching URL and 
 *                                       returns response in different types of data objects.
 *          @version          1.0
 *          Created Date      15-APR-2020
*********************************************************************************************/

@RestController
public class LoginRestController {

	@Autowired
	private AccountLoginService ser;

	@Autowired
	@Qualifier("authenticatemap")
	private Map<String, Customer> authMap;
	
	/***********************************************************************************************
	 * Method                 :getLogin
     * Description            :To provide the service of login for the "/login" URL.
	 * @param userId          :User ID(String)
	 * @param password        :password (String)
	 * @returns String        :Appropriate Message if not throws LoginException,
	 *                                    AccountException,CustomerException
	 * @throws LoginException
	 * It is raised if user does not login.
     * Created By          :Poojith
     * Created Date        :15-APR-2020                          
    ************************************************************************************************/
	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String getLogin(@RequestParam("userId") String userId, @RequestParam("password") String password)
			throws LoginException {
		Customer user = ser.doLogin(userId, password);

		String token = ser.encryptUser(user);
		authMap.put(token, user);
		return token;
	}
	
	/***********************************************************************************************
	 * Method                 :logout
     * Description            :To provide the service of logout for the "/login" URL.
	 * @param token           :token(String)
	 * @param req             :HttpServletRequest
	 * @returns String        :Appropriate Message
     * Created By          :Poojith
     * Created Date        :15-APR-2020                          
    ************************************************************************************************/
	@CrossOrigin
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(@RequestHeader("userId") String token, HttpServletRequest req) {
		authMap.remove(token);
		return "logged out";
	}

}
