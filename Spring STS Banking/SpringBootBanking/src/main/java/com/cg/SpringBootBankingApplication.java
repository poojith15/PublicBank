package com.cg;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cg.banking.entity.Customer;
import com.cg.banking.util.MailConstants;
import com.cg.banking.web.LoggerInterceptor;
import com.cg.banking.web.AdminInterceptor;
import com.cg.banking.web.LoginInterceptor;

@SpringBootApplication
public class SpringBootBankingApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBankingApplication.class, args);
	}

	@Bean(name = "authenticatemap")
	public Map<String, Customer> getAuthenticateMap() {
		return new HashMap<>();
	}

	
	@Autowired
	public LoginInterceptor loginInterceptor;

	@Autowired
	public AdminInterceptor adminInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns(new String[] { "/doTxn", "/viewcustomer/*", "/viewaccountbyaccid/*",
						"/viewaccountsbycustid/*", "/addloanrequest", "/viewloanrequeststatus/*",
						"/viewloanrequestsbycustid/*", "/getTxn/*", "/getTxn" });
		registry.addInterceptor(adminInterceptor).addPathPatterns(new String[] { "/addcustomer", "/editCustomer",
				"/addaccount", "/editaccount", "/viewnewloanrequests", "/processLoan/*" });
	}

}
