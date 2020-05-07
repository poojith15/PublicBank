package com.cg.banking.web;

import java.io.FileOutputStream;
import java.io.IOException;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cg.banking.exceptions.AccountException;
import com.cg.banking.exceptions.CustomerException;
import com.cg.banking.service.CustomerService;
import com.cg.banking.util.AccountConstants;
import com.cg.banking.util.MailConstants;
import com.cg.banking.dto.AccountMessage;
import com.cg.banking.dto.CustomerForm;
import com.cg.banking.entity.Customer;

/**************************************************************************************
 *          @author           Saineel
 *          Description       It is a Rest Controller class that provides the suitable
 *                                       Customer Management methods for the given 
 *                                       matching URL and returns response in different
 *                                       types of data objects.
 *          @version          1.0
 *          Created Date      12-APR-2020
***************************************************************************************/

@RestController
public class CustomerRestController {

	@Autowired
	public CustomerService service;
	
	@Value("${imgpath}")
	private String imgPath;

	/***********************************************************************************************
	 * Method                 :addCustomer
     * Description            :To provide the service of adding customer for the "/addcustomer" URL.
	 * @param custForm        :CustomerForm instance
	 * @param request         :HttpServletRequest instance
	 * @returns AccountMessage:Appropriate Message if not throws LoginException,
	 *                                    CustomerException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws CustomerException
	 * It is raised account is inactive.
     * Created By          :Sreeram
     * Created Date        :13-APR-2020                          
    ************************************************************************************************/
	@CrossOrigin
	@PostMapping("/addcustomer")
	public AccountMessage addCustomer(@RequestBody CustomerForm custForm, HttpServletRequest request)
			throws CustomerException, LoginException {
		try {
			if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
				String custID = service.addCustomer(custForm);
				return new AccountMessage(
						AccountConstants.CUSTOMER_CREATED + AccountConstants.GENERATED_CUSTOMER + custID);
			}
			throw new LoginException(AccountConstants.PLEASE_LOGIN);
		} catch (DataIntegrityViolationException ex) {
			throw new CustomerException("Customer ID already exists");
		}
	}

	/***********************************************************************************************
	 * Method                 :editCustomer
     * Description            :To provide the service of editing customer for the "/editcustomer" URL.
	 * @param cust            :Customer instance
	 * @param request         :HttpServletRequest instance
	 * @returns AccountMessage:Appropriate Message if not throws LoginException.
	 * @throws LoginException
	 * It is raised if user does not login.
     * Created By          :Sreeram
     * Created Date        :13-APR-2020                          
    ************************************************************************************************/
	@CrossOrigin
	@PutMapping("/editCustomer")
	public AccountMessage editCustomer(@RequestBody Customer cust, HttpServletRequest request) throws LoginException {
	//	if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			service.editCustomer(cust);
			return new AccountMessage(AccountConstants.CUSTOMER_EDITED);
	//	}
	//	throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}
	/*********************************************************************************************************
	 * Method                 :viewCustomer
     * Description            :To provide the service of viewing customer for the "/viewcustomer/{custid}" URL.
	 * @param custId          :Customer ID(String)
	 * @param request         :HttpServletRequest instance
	 * @returns Customer      :instance if not throws LoginException,
	 *                                    CustomerException.
	 * @throws LoginException
	 * It is raised if user does not login.
	 * @throws CustomerException
	 * It is raised account is inactive.
     * Created By          :Sreeram
     * Created Date        :13-APR-2020                          
    **********************************************************************************************************/
	@CrossOrigin
	@GetMapping("/viewcustomer/{custid}")
	public Customer viewCustomer(@PathVariable("custid") String custId, HttpServletRequest request)
			throws CustomerException, LoginException {
		//if ((boolean) request.getAttribute(AccountConstants.AUTH_FLAG)) {
			Customer cust = service.viewCustomer(custId);
			return cust;
//		}
//		throw new LoginException(AccountConstants.PLEASE_LOGIN);
	}
	
	@CrossOrigin
	@PostMapping("/upload")
	public String uploadImage(@RequestParam("txtFile") MultipartFile file,@RequestParam("txtCustId") String  custId) throws IOException {
		byte[] arr=file.getBytes();
		FileOutputStream fos=new FileOutputStream(imgPath+custId+MailConstants.IMG_TYPE);
		fos.write(arr);
		fos.close();
		return MailConstants.IMG_UPLOADED;
	}

}
