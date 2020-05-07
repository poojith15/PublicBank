import { Component, OnInit } from '@angular/core';
import { TransactionServiceService } from '../transaction-service.service';
import { AccTransaction } from '../acc-transaction';
import { AccReportForm } from '../acc-report-form';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-get-no-txn',
  templateUrl: './get-no-txn.component.html',
  styleUrls: ['./get-no-txn.component.css']
})
export class GetNoTxnComponent implements OnInit {
listTxn:AccTransaction[]=[];
txnListFlag:boolean;
errorMsg:string;
accId:string;
custId:string;
username:string;
accounts:any=[];
showFlag:boolean;
  constructor(private txnSer:TransactionServiceService,private accSer:AccountService) { }

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

   getNoTxns(){
       this.txnSer.getNoTxn(this.accId).subscribe(data=>{this.listTxn=data;this.errorMsg=undefined;this.txnListFlag=true},
        error=>{this.errorMsg=JSON.parse(error.error).message;this.txnListFlag=false});
   }

}
