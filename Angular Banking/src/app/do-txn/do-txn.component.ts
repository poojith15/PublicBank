import { Component, OnInit, ViewChild } from '@angular/core';
import { TransferFundForm } from '../transfer-fund-form';
import { TransactionServiceService } from '../transaction-service.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AccountService } from '../account.service';
import { Account } from '../account';

@Component({
  selector: 'app-do-txn',
  templateUrl: './do-txn.component.html',
  styleUrls: ['./do-txn.component.css']
})
export class DoTxnComponent implements OnInit {
msg:string;
errorMsg:string;

accounts:any=[];
txnFund:TransferFundForm=new TransferFundForm();

username:string;
custId:string
role:string;

adminFlag:boolean=false;
userFlag:boolean=false;

@ViewChild("accfrm")
form:NgForm;

  constructor(private txnSer:TransactionServiceService,private accSer:AccountService) { }

  ngOnInit() {
    let token = localStorage.getItem("token");
    if(token != null)
    {
        this.custId= this.txnSer.decrypt(token.split('-')[0]);
        this.username=this.accSer.decrypt(token.split('-')[1]);
        this.role=this.accSer.decrypt(token.split('-')[2]);
        
        if(this.role=="admin"){
            this.adminFlag=true;
            this.userFlag=false;
        }
        else{
          this.accSer.viewAccounts(this.custId).subscribe(data=>{this.accounts=data;},
            error=>{this.errorMsg=error.error.message});
            this.adminFlag=false;
            this.userFlag=true;
        }

       /**  this.accSer.viewAccounts(this.custId).subscribe(data=>{this.accounts=data;
            this.accounts = this.accounts.filter(a=>{!a.accountName.includes("loan")})},
              error=>{this.errorMsg=error.error.message});**/
    }
    else{
      this.errorMsg="You are not Authenticated.";
      this.adminFlag=false;
      this.userFlag=false;
    }
  }
  TransferFund(){
    // this.txnser.doTxn(this.txnFund).subscribe(data=>{this.msg=data;this.txnFund=new TransferFundForm();
    // this.errorMsg=undefined},
    // error=>{this.errorMsg=JSON.parse(error.error).message;console.log(error);
    //   this.msg=undefined});
    this.txnSer.doTxn(this.txnFund).subscribe(data=>{this.msg=data,this.form.resetForm()},
    error=>{this.errorMsg=JSON.parse(error.error).message,this.msg=undefined});
  }
}
