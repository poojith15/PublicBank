import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { AccountConstants } from './account-constants';
import { LoanRequestForm } from './loan-request-form';

@Injectable({
  providedIn: 'root'
})
export class LoanServiceService {

  constructor(private http:HttpClient){}

  public addLoanRequest(form:LoanRequestForm):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.post(AccountConstants.ADD_LOAN_REQUEST_URL,form,{headers:httpHeaders});
}
public viewLoanRequestStatus(loanReqId:string):Observable<any>{
  let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.get(AccountConstants.VIEW_LOAN_REQUEST_STATUS_URL+"/"+loanReqId,{headers:httpHeaders});
}
public viewLoanRequestsByCustId(custId:string):Observable<any>{
  let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.get(AccountConstants.VIEW_LOAN_REQUEST_BY_CUSTID_URL+"/"+custId,{headers:httpHeaders});
}
public viewLoanRequests():Observable<any>{
  let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
  return this.http.get(AccountConstants.VIEW_LOAN_REQUESTS_URL,{headers:httpHeaders});
}
public processLoanRequests(loanReqId:string):Observable<any>{
  let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
  return this.http.get(AccountConstants.PROCESS_LOAN_REQUEST_URL+"/"+loanReqId,{headers:httpHeaders});
}


  decrypt(token:string){
    let str="";
    for(let i=0;i<token.length;++i){
      str=str+String.fromCharCode(token.charCodeAt(i)-3);
    }
    console.log(str);
    return str;
  }
}
