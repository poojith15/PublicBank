import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable } from 'rxjs';
import { AccountConstants } from './account-constants';
import { AccountForm } from './account-form';

import { CustomerForm } from './customer-form';

@Injectable({
  providedIn: 'root'
})
export class AccountService {

  constructor(private http:HttpClient){}
  
  public addAccount(form:AccountForm):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
       return this.http.post(AccountConstants.ADD_ACCOUNT_URL,form,{headers:httpHeaders});
  }
  public editAccount(account:any):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
       return this.http.put(AccountConstants.EDIT_ACCOUNT_URL,account,{headers:httpHeaders});
  }
  public viewAccount(accId:string):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
       return this.http.get(AccountConstants.VIEW_ACCOUNT_URL+"/"+accId,{headers:httpHeaders});
  }
  public viewAccounts(custId:string):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.get(AccountConstants.VIEW_ACCOUNTS_URL+"/"+custId,{headers:httpHeaders});
  }

  public addCustomer(form:CustomerForm):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.post(AccountConstants.ADD_CUSTOMER_URL,form,{headers:httpHeaders});
  }
  public editCustomer(cust:any):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.put(AccountConstants.EDIT_CUSTOMER_URL,cust,{headers:httpHeaders});
  }
  public viewCustomer(custId:string):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.get(AccountConstants.VIEW_CUSTOMER_URL+"/"+custId,{headers:httpHeaders});
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
