import { Component, OnInit } from '@angular/core';
import { AccountService } from '../account.service';

@Component({
  selector: 'app-cust-details',
  templateUrl: './cust-details.component.html',
  styleUrls: ['./cust-details.component.css']
})
export class CustDetailsComponent implements OnInit {

  cust:any;
  userName:string;
  constructor(private service:AccountService){}
  ngOnInit(){
    let token = localStorage.getItem('token');
    if(token != null){
      let custId= this.service.decrypt(token.split('-')[0]);
      this.userName= this.service.decrypt(token.split('-')[1]);
      this.service.viewCustomer(custId).subscribe(data=>this.cust=data);
    }
  }

}
