import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AddLoanRequestComponent } from './add-loan-request/add-loan-request.component';
import { DoTxnComponent } from './do-txn/do-txn.component';
import { GetNoTxnComponent } from './get-no-txn/get-no-txn.component';
import { HomeComponent } from './home/home.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { AccountComponent } from './account/account.component';
import { CustDetailsComponent } from './cust-details/cust-details.component';
import { CustomerComponent } from './customer/customer.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import { GetTxnComponent } from './get-txn/get-txn.component';
import { ViewAccountsComponent } from './view-accounts/view-accounts.component';
import { TransactionsComponent } from './transactions/transactions.component';

@NgModule({
  declarations: [
    AppComponent,
    AddLoanRequestComponent,
    DoTxnComponent,
    GetNoTxnComponent,
    HomeComponent,
    ContactUsComponent,
    AccountComponent,
    CustDetailsComponent,
    CustomerComponent,
    ErrorPageComponent,
    GetTxnComponent,
    ViewAccountsComponent,
    TransactionsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
