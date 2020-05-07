import { Customer } from './customer';

export class Account {
    accountId:string;
    accountName:string;
    accountStatus:string;
    accountBalance:number;
    lastUpdated:Date;
    customer:Customer=new Customer();
}
