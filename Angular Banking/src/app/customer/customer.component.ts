import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../account.service';
import { CustomerForm } from '../customer-form';
import { Customer } from '../customer';


@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit {

  cform:CustomerForm = new CustomerForm();
  cust:Customer=new Customer();
  msg:string;
  errorMsg:string;

  adminFlag=false;
  userFlag=false;

  customer_id:string;
  userName:string;
  role:string;
  
  constructor(private service:AccountService){}
  
  @ViewChild('accfrm')
  form:NgForm;
  

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
        this.service.viewCustomer(this.customer_id).subscribe(data=>{ this.cust=data;
          this.showAddFlag=false;
          this.showEditFlag=false;
          this.showViewFlag=false;
         },
          error=>{console.log(error); this.errorMsg=error.error.message});

        this.adminFlag=false;
        this.userFlag=true;
      }
    }
  }

  showEditFlag=false;
  showViewFlag=false;
  showAddFlag=false;
  
  
  
  showAddCustomer(){
    this.showAddFlag=true;
    this.showEditFlag=false;
    this.showViewFlag=false;
  }
  
  showViewCustomer(){
    this.showAddFlag=false;
    this.showEditFlag=false;
    this.showViewFlag=true;
  }

  showViewCustomerDetails(){
    this.service.viewCustomer(this.customer_id).subscribe(data=>{ this.cust=data;
                                                                  this.showAddFlag=false;
                                                                  this.showEditFlag=false;
                                                                  this.showViewFlag=false;
          
                                                          },
                                  error=>{console.log(error); this.errorMsg=error.error.message});
    
  }


  
  addCustomer(){
    this.service.addCustomer(this.cform).subscribe(data=>{this.msg= data.message;this.form.resetForm()},
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }
  
  viewCustomer(){
    this.service.viewCustomer(this.customer_id).subscribe(data=>{this.cust=data; this.customer_id='';
                                                            this.showAddFlag=false;
                                                            this.showEditFlag=true;
                                                            this.showViewFlag=false;

                                                          },
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }

  editCustomer(cust:Customer){
    this.service.editAccount(cust).subscribe(data=>{this.msg= data.message;this.form.resetForm()},
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }

  

}
