import { Component, OnInit, ViewChild } from '@angular/core';
import { AccountForm } from '../account-form';
import { AccountService } from '../account.service';
import { NgForm } from '@angular/forms';
import { Account } from '../account';
import { TransactionServiceService } from '../transaction-service.service';
import { AccTransaction } from '../acc-transaction';
import { Router } from '@angular/router';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit {

  listTxn:AccTransaction[]=[];
  aform:AccountForm = new AccountForm();
  account:Account=new Account();
  accounts:Account[]=[];

  msg:string;
  errorMsg:string;

  adminFlag=false;
  userFlag=false;

  accountId:string;
  customer_id:string;
  userName:string;
  role:string;
  
  constructor(private service:AccountService,private txnSer:TransactionServiceService,private router:Router){}
  
  @ViewChild('accfrm')
  form:NgForm;
  
  showAddFlag=false;
  showEditFlag=false;
  showViewFlag=false;
  showDetailsFlag=false;
  showUserTxFlag=false;
  showAdminTxFlag=false;
  

  ngOnInit() {
    let token = localStorage.getItem('token');
    if(token != null){
      this.customer_id= this.service.decrypt(token.split('-')[0]);
      this.userName= this.service.decrypt(token.split('-')[1]);
      this.role=this.service.decrypt(token.split('-')[2]);
     if(this.role=="admin"){
        this.adminFlag=true;
       this.userFlag=false;
     }
     else{
        this.service.viewAccounts(this.customer_id).subscribe(data=>{this.accounts=data;
           this.showAddFlag=false;
          this.showEditFlag=false;
           this.showViewFlag=false;
           this.showDetailsFlag=true;
        },
          error=>{console.log(error); this.errorMsg=error.error.message});
        this.adminFlag=false;
        this.userFlag=true;
     }
   }
  }

  showAddAccount(){
    this.showAddFlag=true;
    this.showEditFlag=false;
    this.showViewFlag=false;
    this.errorMsg=undefined;
    this.msg=undefined;

  }
  showViewAccount(){
    this.showAddFlag=false;
    this.showEditFlag=false;
    this.showViewFlag=true;
    this.errorMsg=undefined;
    this.msg=undefined;
  }

  addAccount(){
    this.errorMsg=undefined;
    this.msg=undefined;
    this.service.addAccount(this.aform).subscribe(data=>{this.msg= data.message;this.form.resetForm()},
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }

  viewAccount(){
    this.errorMsg=undefined;
    this.msg=undefined;
    this.service.viewAccount(this.accountId).subscribe(data=>{this.account=data; this.accountId='';
            this.showAddFlag=false;
            this.showEditFlag=true;
            this.showViewFlag=false;
            this.errorMsg=undefined;
          },
            error=>{console.log(error); this.errorMsg=error.error.message});
  }

  editAccount(account:any){
    this.errorMsg=undefined;
    this.msg=undefined;
    this.service.editAccount(this.account).subscribe(data=>{this.msg= data.message;},
            error=>{console.log(error); this.errorMsg=error.error.message});
  }


  viewTransactions(accountId:string){
    this.errorMsg=undefined;
    this.msg=undefined;
    this.txnSer.getNoTxn(accountId).subscribe(data=>{
                                                      this.listTxn=data;
                                                      this.errorMsg=undefined;
                                                      if(this.role=="admin"){
                                                        this.showUserTxFlag=false;
                                                        this.showAdminTxFlag=true;
                                                        this.showEditFlag=false;
                                                     }
                                                     else{
                                                        this.showUserTxFlag=true;
                                                        this.showAdminTxFlag=false;
                                                        this.showDetailsFlag=false;
                                                     }
                                                    },
      error=>{this.errorMsg=JSON.parse(error.error).message;});
    }
    goBack(){
      if(this.role=="admin"){
        this.showEditFlag=true;
        this.showAdminTxFlag=false;
     }
     else{
      this.showUserTxFlag=false;
      this.showDetailsFlag=true;
     }
      
    }

    gotoPassbook(){
      this.router.navigateByUrl("/passbook");
    }

}
