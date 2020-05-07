import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { TransferFundForm } from './transfer-fund-form';
import { Observable } from 'rxjs';
import { AccReportForm } from './acc-report-form';
import { AccountConstants } from './account-constants';

@Injectable({
  providedIn: 'root'
})
export class TransactionServiceService {
userName:string;
  constructor(private http:HttpClient) { }

  public doTxn(txnFrm:TransferFundForm):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.post(AccountConstants.TRANSFER_FUND_URL,txnFrm,{headers:httpHeaders,responseType:'text'});
  }

  public getTxn(accReportForm:AccReportForm):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.post(AccountConstants.VIEW_TXNS_URL,accReportForm,{headers:httpHeaders});
  }

  public getNoTxn(str:string):Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.get(AccountConstants.VIEW_NO_TXNS_URL+str,{headers:httpHeaders});
  }
  decrypt(token:string){
    let str="";
    for(let con =0; con < token.length; ++con){
         str=str+String.fromCharCode(token.charCodeAt(con)-3);
    }
         console.log(str);
         return str;
  }

  public doLogin(userId:string,password:string):Observable<any>{
     let postData=new FormData();
     postData.append('userId',userId);
     postData.append('password',password);
     return this.http.post(AccountConstants.LOGIN_URL,postData,{responseType:'text'});
  }

  public doLogout(){
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
    return this.http.get(AccountConstants.LOGOUT_URL,{headers:httpHeaders,responseType:'text'})
  }


  public download():Observable<any>{
    let utoken=localStorage.getItem("token");
    if(utoken==null)utoken="";
    const httpHeaders=new HttpHeaders({"userId":utoken});
        return this.http.get(AccountConstants.DOWNLOAD_TRANSACTIONS_URL,{headers:httpHeaders,responseType:'blob' as'json'});
      } 

    public uploadImage(custId:string,custImg:File){
          let postData=new FormData();
          postData.append('txtCustId',custId);
          postData.append('txtFile',custImg);
          return this.http.post(AccountConstants.IMG_UPLOAD_URL,postData,{responseType:'text'});
        }
}
