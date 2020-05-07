import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { AccountService } from '../account.service';
import { CustomerForm } from '../customer-form';
import { Customer } from '../customer';
import { TransactionServiceService } from '../transaction-service.service';
import { Router } from '@angular/router';


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
  
  constructor(private service:AccountService,private txnSer:TransactionServiceService,private router:Router){}
  
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
    this.msg=undefined;
    this.errorMsg=undefined;
  }
  
  showViewCustomer(){
    this.showAddFlag=false;
    this.showEditFlag=false;
    this.showViewFlag=true;
    this.msg=undefined;
    this.errorMsg=undefined;
  }

  showViewCustomerDetails()
  {
    this.msg=undefined;
    this.errorMsg=undefined;
    this.service.viewCustomer(this.customer_id).subscribe(data=>{ this.cust=data;
                                                                  this.showAddFlag=false;
                                                                  this.showEditFlag=false;
                                                                  this.showViewFlag=false;
          
                                                          },
                                  error=>{console.log(error); this.errorMsg=error.error.message});  
  }


  
  addCustomer(){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.service.addCustomer(this.cform).subscribe(data=>{this.msg= data.message;
                                                            this.form.resetForm()
      let str=this.msg.split('-')[1];
      this.txnSer.uploadImage(str,this.Eimg).subscribe(data=>
                {this.msg=data;this.errorMsg=undefined;this.router.navigateByUrl("/customer");
                alert("Added Successfully! Your Customer ID :"+str+ ". Please login Again");
                },error=>{this.errorMsg=error.error.message;this.msg=undefined});
    },
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }
  
  viewCustomer(){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.service.viewCustomer(this.customer_id).subscribe(data=>{this.cust=data; this.customer_id='';
                                                            this.showAddFlag=false;
                                                            this.showEditFlag=true;
                                                            this.showViewFlag=false;

                                                          },
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }

  editCustomer(cust:Customer){
    this.msg=undefined;
    this.errorMsg=undefined;
    this.service.editCustomer(cust).subscribe(data=>{this.msg= data.message;

      this.txnSer.uploadImage(cust.customerId,this.Eimg).subscribe(data=>
        {this.msg=data;this.errorMsg=undefined;this.router.navigateByUrl("/customer");
          alert("Edited Successfully Please login Again");
      },error=>{this.errorMsg=error.error.message;this.msg=undefined});
    
                    },
                                  error=>{console.log(error); this.errorMsg=error.error.message});
  }



  Eimg:File;
onFileChanged(event:any){
this.Eimg=event.target.files[0];
} 
  


}
