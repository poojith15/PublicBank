import { Customer } from './customer';

export class LoanRequest {
    loanRequestId:string;
    loanAmount:number;
    loanType:string;
    loanTenure:number;
    reqStatus:string;
    dateOfRequest:Date;
    annualIncome:number;
    customer:Customer=new Customer()
}
