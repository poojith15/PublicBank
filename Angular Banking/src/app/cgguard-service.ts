import { CanActivate } from '@angular/router/src/utils/preactivation';
import { Router } from '@angular/router';
import { TransactionServiceService } from './transaction-service.service';

export class CgguardService implements CanActivate{
    path: import("@angular/router").ActivatedRouteSnapshot[];
    route: import("@angular/router").ActivatedRouteSnapshot;
  
    constructor(private router:Router,private txnser:TransactionServiceService){}
    canActivate(route:import("@angular/router").ActivatedRouteSnapshot,
     state: import("@angular/router").RouterStateSnapshot):boolean|
     import ("@angular/router").UrlTree|import("rxjs").Observable<boolean
     |import("@angular/router").UrlTree>|Promise<boolean
     |import("@angular/router").UrlTree>
     {
         let token=localStorage.getItem("token");
         if(localStorage.getItem("token")!=null){
             let arr=token.split("-");
             let userName=this.txnser.decrypt(arr[1]);
             let role=this.txnser.decrypt(arr[2]);
             console.log(route.data)
             if(route.data.role==undefined) return true;
             if(route.data.role!=undefined&&role!=null&&route.data.role==role){
                 return true;
             }
         }
         this.router.navigateByUrl("error");
         return false;
     }
    

}
