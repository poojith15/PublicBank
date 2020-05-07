import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DoTxnComponent } from './do-txn/do-txn.component';
import { AppComponent } from './app.component';
import { AddLoanRequestComponent } from './add-loan-request/add-loan-request.component';
import { HomeComponent } from './home/home.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { AccountComponent } from './account/account.component';
import { CustomerComponent } from './customer/customer.component';
import { TransactionsComponent } from './transactions/transactions.component';

const routes: Routes = [
  {path:'doTxn',component:DoTxnComponent},
  {path:'app',component:AppComponent},
  {path:'loan',component:AddLoanRequestComponent},
  {path:'home',component:HomeComponent},
  {path:'contact',component:ContactUsComponent},
  {path:'account',component:AccountComponent},
  {path:'customer',component:CustomerComponent},
  {path:'passbook',component:TransactionsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
