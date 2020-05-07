import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { AccountForm } from './account-form';
import { AccountConstants } from './account-constants';

@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {

  constructor(private http:HttpClient){}
public addAccount(form:AccountForm):Observable<any>{
       return this.http.post(AccountConstants.ADD_ACCOUNT_URL,form);
}

public viewAccount(accId:string):Observable<any>{
       return this.http.get(AccountConstants.VIEW_ACCOUNTS_URL+"/"+accId);
}

decrypt(token:string){
  let str="";
  for(let con =0; con < token.length; ++con){
       str=str+String.fromCharCode(token.charCodeAt(con)-3);
       console.log(str);
       return str;
  }
}

}
