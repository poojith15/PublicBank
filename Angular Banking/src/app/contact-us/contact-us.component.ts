import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css']
})
export class ContactUsComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  showP1Flag:boolean=false;
  showP2Flag:boolean=false;
  showP3Flag:boolean=false;
  showP4Flag:boolean=false;
  viewP1(){
    this.showP1Flag=true;
    this.showP2Flag=false;
    this.showP3Flag=false;
    this.showP4Flag=false;
  }
  viewP2(){
    this.showP1Flag=false;
    this.showP2Flag=true;
    this.showP3Flag=false;
    this.showP4Flag=false;
  }
  viewP3(){
    this.showP1Flag=false;
    this.showP2Flag=false;
    this.showP3Flag=true;
    this.showP4Flag=false;
  }
  viewP4(){
    this.showP1Flag=false;
    this.showP2Flag=false;
    this.showP3Flag=false;
    this.showP4Flag=true;
  }
}
