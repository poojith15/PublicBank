import { Account } from './account';

export class AccTransaction {
    transaccountId:number;
    transType:string;
    transAmount:number;
    transDescription:string;
    transDate:Date;
    account:Account=new Account();
    
}
