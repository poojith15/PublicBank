import { Component } from '@angular/core';
import { Login } from './login';
import { TransactionServiceService } from './transaction-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'BANK';   
  login:Login=new Login();
  token:string=localStorage.getItem("token");
  msg:string;
  errorMsg:string;
  loginFlag:boolean=true;
  showLoginFlag:boolean=true;

  constructor(private txnser:TransactionServiceService,private router:Router){
  }

  showLogin(){
    this.showLoginFlag=true;
  }


  doLogin(){
   this.txnser.doLogin(this.login.uname,this.login.pwd).subscribe(data=>{localStorage.setItem("token",data);
      let arr=data.split("-");
      this.token=localStorage.getItem("token");
      this.txnser.userName=this.txnser.decrypt(arr[1]);
      this.router.navigateByUrl("/home");
      this.showLoginFlag=false;
      this.loginFlag=false;
    },
    error=>{console.log(error);this.login.pwd="";this.errorMsg=JSON.parse(error.error).message}); 

    //this.router.navigateByUrl("/home");
    //  this.showLoginFlag=false;
     // this.loginFlag=false;
  }
  doLogOut(){
    this.txnser.doLogout().subscribe(data=>{console.log(data);
      localStorage.removeItem("token");
      alert("You have logged out");
      this.login.pwd="";
      this.showLoginFlag=true;
     this.loginFlag=true;
      this.token=undefined;
     });
 
     // alert("You have logged out");
     // this.login.pwd="";
     // this.showLoginFlag=true;
     // this.loginFlag=true;
  }
  onActivate(event){
    window.scroll(0,0);
  }
}
