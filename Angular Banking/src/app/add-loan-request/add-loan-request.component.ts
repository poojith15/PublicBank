import { Component, OnInit, ViewChild } from '@angular/core';
import { LoanServiceService } from '../loan-service.service';
import { LoanRequestForm } from '../loan-request-form';
import { NgForm } from '@angular/forms';
import { LoanRequest } from '../loan-request';

@Component({
  selector: 'app-add-loan-request',
  templateUrl: './add-loan-request.component.html',
  styleUrls: ['./add-loan-request.component.css']
})
export class AddLoanRequestComponent implements OnInit {

  aform:LoanRequestForm = new LoanRequestForm();
  loanRequest:LoanRequest=new LoanRequest;
  loanRequests:LoanRequest[]=[];
  msg:string;
  errorMsg:string;

  customer_id:string;
  userName:string;
  role:string;
  loanReqId:string;

  adminFlag=false;
  userFlag=false;

  @ViewChild('loanfrm')
  form:NgForm;

  viewNewLoanRequestsFlag=false;
  addLoanRequestFlag=false;
  viewLoanRequestStatusFlag=false;
  viewLoanRequestOfCustomerFlag=false;

  showViewFlag=false;

  constructor(private service:LoanServiceService) { }

  ngOnInit() {
    let token = localStorage.getItem('token');
    if(token != null){
      this.customer_id= this.service.decrypt(token.split('-')[0]);
      this.userName= this.service.decrypt(token.split('-')[1]);
      this.role=this.service.decrypt(token.split('-')[2]);
      if(this.role=='admin'){
        this.adminFlag=true;
        this.userFlag=false;
      }
      else{
        this.adminFlag=false;
        this.userFlag=true;
      }
    }
  }
  

  showAddLoanRequest(){
    this.viewNewLoanRequestsFlag=false;
    this.addLoanRequestFlag=true;
    this.viewLoanRequestStatusFlag=false;
    this.viewLoanRequestOfCustomerFlag=false;
    this.msg=undefined;
    this.errorMsg=undefined;
  }
  showViewLoanRequestStatus(){
    this.viewNewLoanRequestsFlag=false;
    this.addLoanRequestFlag=false;
    this.viewLoanRequestStatusFlag=true;
    this.viewLoanRequestOfCustomerFlag=false;
    this.msg=undefined;
    this.errorMsg=undefined;
  }



  addLoanRequest(){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.aform.customerId=this.customer_id;
    this.service.addLoanRequest(this.aform).subscribe(data=>{this.msg= data.message;
                                                            this.form.resetForm();
                                                          this.errorMsg=undefined},
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }
  viewLoanRequestStatus(){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.addLoanRequest=null;
    this.service.viewLoanRequestStatus(this.loanReqId).subscribe(data=>{this.loanRequest=data; 
                                                                        
                                      this.showViewFlag=true;        
          },
            error=>{console.log(error); this.errorMsg=error.error.message;this.msg=undefined;});
  }

  viewLoanRequestOfCustomer(){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.service.viewLoanRequestsByCustId(this.customer_id).subscribe(data=>{
                                          this.loanRequests=data;
                                          this.viewNewLoanRequestsFlag=false;
                                          this.addLoanRequestFlag=false;
                                          this.viewLoanRequestStatusFlag=false;
                                          this.viewLoanRequestOfCustomerFlag=true;
                                          this.errorMsg=undefined;
                            },
      error=>{console.log(error); this.errorMsg=error.error.message;this.loanRequests=[];});
  }

  viewNewLoanRequests(){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.service.viewLoanRequests().subscribe(data=>{this.loanRequests=data;
                                            this.viewNewLoanRequestsFlag=true;
                                            this.addLoanRequestFlag=false;
                                            this.viewLoanRequestStatusFlag=false;
                                            this.viewLoanRequestOfCustomerFlag=false;
    },
      error=>{console.log(error); this.errorMsg=error.error.message;this.loanRequests=[];});
  }
  processLoanRequests(loanRequestId:string){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.service.processLoanRequests(loanRequestId).subscribe(data=>{this.msg= data.message;
                                                                  this.viewNewLoanRequests();
                                                                this.errorMsg=undefined;},
      error=>{console.log(error); this.errorMsg=error.error.message;this.msg=undefined;});
  }

  resetMenu(){
    this.viewNewLoanRequestsFlag=false;
    this.addLoanRequestFlag=false;
    this.viewLoanRequestStatusFlag=false;
    this.viewLoanRequestOfCustomerFlag=false;
  }

}
