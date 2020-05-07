package com.cg.banking.web;

import java.util.Map;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cg.banking.entity.Customer;
import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.CustomerException;

/********************************************************************************************
 *          @author           Poojith
 *          Description       It is a Admin Interceptor class that checks the userId and role
 *                                       changes the authentication flag accordingly.
 *          @version          1.0
 *          Created Date      11-APR-2020
*********************************************************************************************/

@Component
public class AdminInterceptor implements HandlerInterceptor {

	@Autowired
	@Qualifier("authenticatemap")
	private Map<String, Customer> authMap;

	private Logger logger = LoggerFactory.getLogger(AdminInterceptor.class);

	/***********************************************************************************************
	 * Method                 :preHandle
	 * Description            :this method is invoked before accessing the controller
	 * @param response        :HttpServletResponse
	 * @param request         :HttpServletRequest instance
	 * @param handler         :Object
	 * @returns Boolean       :true, admin validation completed if not throws Exception.
	 * @throws Exception
     * Created By             :Poojith
     * Created Date           :13-APR-2020                          
    ************************************************************************************************/
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String token = request.getHeader("userId");
		Customer customer = (Customer) authMap.get(token);

		logger.info("received tokenid " + request.getHeader("userId"));
		if (customer != null && customer.getRole().equals("admin"))
			request.setAttribute("authFlag", true);
		else
			request.setAttribute("authFlag", false);
		return true;
	}

	/***********************************************************************************************
	 * Method                 :postHandle
     * Description            :this method is invoked after accessing the controller
	 * @param response        :HttpServletResponse
	 * @param request         :HttpServletRequest instance
	 * @param handler         :Object
	 * @param modelAndView    :ModelAndView
	 * @returns void          :if not throws Exception.
	 * @throws Exception
     * Created By             :Poojith
     * Created Date           :13-APR-2020                          
    ************************************************************************************************/
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}
	/***********************************************************************************************
	 * Method                 :afterCompletion
	 * Description            :this method is invoked before sending the response
	 * @param response        :HttpServletResponse
	 * @param request         :HttpServletRequest instance
	 * @param handler         :Object
	 * @param ex              :Exception
	 * @returns void          :if not throws Exception.
	 * @throws Exception
     * Created By             :Poojith
     * Created Date           :13-APR-2020                          
    ************************************************************************************************/
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
