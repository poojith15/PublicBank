import { Component, OnInit } from '@angular/core';
import { LoanServiceService } from '../loan-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {


  msg:string;
  errorMsg:string;

  customer_id:string;
  userName:string;
  role:string;

  userFlag:boolean=true;
  adminFlag:boolean=true;


  showP1Flag:boolean=false;
  showP2Flag:boolean=false;
  showP3Flag:boolean=false;
  showP4Flag:boolean=false;
  showP5Flag:boolean=false;
  viewP1(){
    this.showP1Flag=true;
    this.showP2Flag=false;
    this.showP3Flag=false;
    this.showP4Flag=false;
    this.showP5Flag=false;
  }
  viewP2(){
    this.showP1Flag=false;
    this.showP2Flag=true;
    this.showP3Flag=false;
    this.showP4Flag=false;
    this.showP5Flag=false;
  }
  viewP3(){
    this.showP1Flag=false;
    this.showP2Flag=false;
    this.showP3Flag=true;
    this.showP4Flag=false;
    this.showP5Flag=false;
  }
  viewP4(){
    this.showP1Flag=false;
    this.showP2Flag=false;
    this.showP3Flag=false;
    this.showP4Flag=true;
    this.showP5Flag=false;
  }
  viewP5(){
    this.showP1Flag=false;
    this.showP2Flag=false;
    this.showP3Flag=false;
    this.showP4Flag=false;
    this.showP5Flag=true;
  }

  gotoAccount(){
    this.router.navigateByUrl("/account");
  }
  gotoCustomer(){
    this.router.navigateByUrl("/customer");
  }
  gotoLoan(){
    this.router.navigateByUrl("/loan");
  }
  gotoTransferFund(){
    this.router.navigateByUrl("/doTxn");
  }
  gotoPassbook(){
    this.router.navigateByUrl("/passbook");
  }

  constructor(private service:LoanServiceService,private router:Router) { }

  ngOnInit() {
    //let token = localStorage.getItem('token');
    //if(token != null){
   //   this.customer_id= this.service.decrypt(token.split('-')[0]);
   //   this.userName= this.service.decrypt(token.split('-')[1]);
  //    this.role=this.service.decrypt(token.split('-')[2]);
   //   if(this.role=='admin'){
 //       this.adminFlag=true;
   //     this.userFlag=true;
 //     }
 //     else{
//        this.adminFlag=true;
 //       this.userFlag=true;
 //     }
 //   }





}





  

}
