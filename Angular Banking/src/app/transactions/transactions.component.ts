import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';
import { TransactionServiceService } from '../transaction-service.service';
import { Account } from '../account';
import { AccTransaction } from '../acc-transaction';
import { AccReportForm } from '../acc-report-form';

@Component({
  selector: 'app-transactions',
  templateUrl: './transactions.component.html',
  styleUrls: ['./transactions.component.css']
})
export class TransactionsComponent implements OnInit {

  rptFrm:AccReportForm=new AccReportForm();
  listTxn:AccTransaction[]=[];
  account:Account=new Account();
  accounts:Account[]=[];

  msg:string;
  errorMsg:string;

  adminFlag=false;
  userFlag=false;

  showUserViewFlag:boolean=false;
  showAdminViewFlag:boolean=false;
  showTxFlag:boolean=false;
  showCustomFlag:boolean=false;

  accountId:string;
  customer_id:string;
  userName:string;
  role:string;


  constructor(private service:AccountService,private txnSer:TransactionServiceService){}

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
        },
          error=>{console.log(error); this.errorMsg=error.error.message});
        this.adminFlag=false;
        this.userFlag=true;
     }
   }
  }




  showAdminTransactions(){
    this.showAdminViewFlag=true;
    this.showCustomFlag=false;
    this.showUserViewFlag=false;
    this.showTxFlag=false;
  }
  showUserTransactions(){
    this.showCustomFlag=false;
    this.showAdminViewFlag=false;
    this.showUserViewFlag=true;
    this.showTxFlag=false;
  }
  showCustomTransaction(){
    this.showCustomFlag=true;
    this.showAdminViewFlag=false;
    this.showUserViewFlag=false;
    this.showTxFlag=false;
  }

  viewAccountTransaction(){
    this.listTxn=[];
    this.txnSer.getNoTxn(this.accountId).subscribe(data=>{
                    this.listTxn=data;
                    this.errorMsg=undefined;
                },
        error=>{this.errorMsg=JSON.parse(error.error).message;});
        this.showTxFlag=true;
  }

  viewCustomTransactions()
  {
    this.txnSer.getTxn(this.rptFrm).subscribe(data=>{
                                this.listTxn=data;
                                this.errorMsg=undefined;
                              },
      error=>{this.errorMsg=JSON.parse(error.error).message;});
      this.showTxFlag=true;
    }


    download(){
            this.txnSer.download().subscribe(data=>{ let blob = new Blob([data], {type: 'application/pdf'});
      
            var downloadURL = window.URL.createObjectURL(data);
            var link = document.createElement('a');
            link.href = downloadURL;
            link.download = "transactions.pdf";
            link.click();}
        );
    }
}


