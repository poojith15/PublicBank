import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';


@Component({
  selector: 'app-view-accounts',
  templateUrl: './view-accounts.component.html',
  styleUrls: ['./view-accounts.component.css']
})
export class ViewAccountsComponent implements OnInit {


  accounts:any=[];
  errorMsg:string;
  userName:string;
  custId:string;
  
  constructor(private service:AccountService){}
  
  ngOnInit(){
    let token = localStorage.getItem('token');
    if(token != null){
      let custId= this.service.decrypt(token.split('-')[0]);
      this.userName= this.service.decrypt(token.split('-')[1]);
      this.service.viewAccounts(custId).subscribe(data=>this.accounts=data,
      error=>this.errorMsg=error.error.message);
    }else this.errorMsg="You are not Authenticated.";
  }

}
