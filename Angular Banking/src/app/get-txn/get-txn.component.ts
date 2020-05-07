import { Component, OnInit } from '@angular/core';
import { TransactionServiceService } from '../transaction-service.service';
import { AccountService } from '../account.service';
import { AccReportForm } from '../acc-report-form';
import { AccTransaction } from '../acc-transaction';

@Component({
  selector: 'app-get-txn',
  templateUrl: './get-txn.component.html',
  styleUrls: ['./get-txn.component.css']
})
export class GetTxnComponent implements OnInit {
  rptFrm:AccReportForm=new AccReportForm();
  listTxn:AccTransaction[]=[];
  custId:string;
accounts:any=[];
username:string;
errorMsg:string;
showFlag:boolean;
txnListFlag:boolean;
  constructor(private txnSer:TransactionServiceService,private accSer:AccountService) {}

  ngOnInit() {
    let token = localStorage.getItem("token");
    if(token != null){
         this.custId= this.txnSer.decrypt(token.split('-')[0]);
    this.username=this.accSer.decrypt(token.split('-')[1]);
         this.accSer.viewAccounts(this.custId).subscribe(data=>{this.accounts=data;
    /**this.accounts = this.accounts.filter(a=>{!a.accountName.includes("loan")})**/},
          error=>{this.errorMsg=error.error.message});
    
    this.showFlag=true;
  }
    else{
    this.errorMsg="You are not Authenticated.";
    this.showFlag=false;
    }
  }
getTxns(){
  this.txnSer.getTxn(this.rptFrm).subscribe(data=>{this.listTxn=data;this.errorMsg=undefined;this.txnListFlag=true},
    error=>{this.errorMsg=JSON.parse(error.error).message;this.txnListFlag=false})
}


}
