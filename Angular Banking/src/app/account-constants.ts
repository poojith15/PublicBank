export class AccountConstants {
    public static SPRING_WEB_URL="http://localhost:8082/banking/";

public static LOGIN_URL= AccountConstants.SPRING_WEB_URL +"login";
public static LOGOUT_URL= AccountConstants.SPRING_WEB_URL +"logout";

public static ADD_ACCOUNT_URL= AccountConstants.SPRING_WEB_URL +"addaccount";
public static VIEW_ACCOUNTS_URL= AccountConstants.SPRING_WEB_URL +"viewaccountsbycustid";
public static VIEW_ACCOUNT_URL= AccountConstants.SPRING_WEB_URL +"viewaccountbyaccid";
public static EDIT_ACCOUNT_URL= AccountConstants.SPRING_WEB_URL +"editaccount";

public static ADD_CUSTOMER_URL= AccountConstants.SPRING_WEB_URL +"addcustomer";
public static EDIT_CUSTOMER_URL= AccountConstants.SPRING_WEB_URL +"editCustomer";
public static VIEW_CUSTOMER_URL= AccountConstants.SPRING_WEB_URL +"viewcustomer";

public static VIEW_TXNS_URL= AccountConstants.SPRING_WEB_URL +"getTxn";
public static VIEW_NO_TXNS_URL= AccountConstants.SPRING_WEB_URL +"getTxn/";
public static TRANSFER_FUND_URL= AccountConstants.SPRING_WEB_URL +"doTxn";

public static ADD_LOAN_REQUEST_URL= AccountConstants.SPRING_WEB_URL +"addloanrequest";
public static VIEW_LOAN_REQUEST_STATUS_URL= AccountConstants.SPRING_WEB_URL +"viewloanrequeststatus";
public static VIEW_LOAN_REQUEST_BY_CUSTID_URL= AccountConstants.SPRING_WEB_URL +"viewloanrequestsbycustid";
public static VIEW_LOAN_REQUESTS_URL= AccountConstants.SPRING_WEB_URL +"viewnewloanrequests";
public static PROCESS_LOAN_REQUEST_URL= AccountConstants.SPRING_WEB_URL +"processLoan";

public static IMG_UPLOAD_URL= AccountConstants.SPRING_WEB_URL +"upload";
}
