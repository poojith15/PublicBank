package com.cg.banking.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/******************************************************************************************
 *          @author           Saineel
 *          Description       It is a Logger Interceptor Advice class that detects the life  
 *                                        cycle events in the server.
 *          @version          1.0
 *          Created Date      11-APR-2020
*******************************************************************************************/
@Component
public class LoggerInterceptor implements HandlerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(LoggerInterceptor.class);

	/***********************************************************************************************
	 * Method                 :afterCompletion
	 * Description            :this method is invoked before sending the response
	 * @param arg1            :HttpServletResponse
	 * @param req             :HttpServletRequest instance
	 * @param obj             :Object
	 * @param arg3            :Exception
	 * @returns void          :if not throws Exception.
	 * @throws Exception
     * Created By             :Poojith
     * Created Date           :13-APR-2020                          
    ************************************************************************************************/
	@Override
	public void afterCompletion(HttpServletRequest req, HttpServletResponse arg1, Object obj, Exception arg3)
			throws Exception {
		HandlerMethod mtd = (HandlerMethod) obj;

		logger.debug(" sending the JSON Response to the browser for the URI " + req.getRequestURI());
	}

	/***********************************************************************************************
	 * Method                 :postHandle
     * Description            :this method is invoked after accessing the controller
	 * @param arg1            :HttpServletResponse
	 * @param req             :HttpServletRequest instance
	 * @param obj             :Object
	 * @param mv              :ModelAndView
	 * @returns void          :if not throws Exception.
	 * @throws Exception
     * Created By             :Poojith
     * Created Date           :13-APR-2020                          
    ************************************************************************************************/
	@Override
	public void postHandle(HttpServletRequest req, HttpServletResponse arg1, Object obj, ModelAndView mv)
			throws Exception {
		HandlerMethod mtd = (HandlerMethod) obj;
		String str = mtd.getMethod().getName() + " method of " + mtd.getBean().getClass().getSimpleName();
		logger.debug(str + " is executed  for the URI " + req.getRequestURI());
	}

	/***********************************************************************************************
	 * Method                 :preHandle
	 * Description            :this method is invoked before accessing the controller
	 * @param arg1            :HttpServletResponse
	 * @param req             :HttpServletRequest instance
	 * @param obj             :Object
	 * @returns Boolean       :true, if not throws Exception.
	 * @throws Exception
     * Created By             :Poojith
     * Created Date           :13-APR-2020                          
    ************************************************************************************************/
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse arg1, Object obj) throws Exception {
		HandlerMethod mtd = (HandlerMethod) obj;
		String str = mtd.getMethod().getName() + " method of " + mtd.getBean().getClass().getSimpleName();
		logger.debug("It is invoking the " + str + "for the URI " + req.getRequestURI());
		return true;
	}

}
