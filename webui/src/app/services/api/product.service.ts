import { Injectable, Inject } from '@angular/core';
import { Observable, ReplaySubject, Subject } from 'rxjs';
import { TranslateService } from './translate.service';
import { ApiRequestService } from './api-request.service';
import { HttpParams} from "@angular/common/http";
import { AppConfig } from '../../app-config';

export interface ProductUpdate{
    id:number;
    etat:number;
}



@Injectable()
export class ProductService {
    constructor(
        private apiRequest: ApiRequestService,
        private translate:TranslateService
    ) {}

    getProducts(page?:number, size?:number): Observable<any> {
        //Create Request URL params
        let me = this;
        let params: HttpParams = new HttpParams();
        params = params.append('page', typeof page === "number"? page.toString():"0");
        params = params.append('size', typeof size === "number"? size.toString():"1000");

        let productList = new Subject<any>(); // Will use this subject to emit data that we want
        this.apiRequest.get('api/products',params)
            .subscribe(jsonResp => {
                let returnObj = jsonResp.items.map(function(v, i, a){
                    let newRow = Object.assign({}, v, {
                        listPrice   : me.translate.getCurrencyString(v.listPrice),
                        standardCost: me.translate.getCurrencyString(v.standardCost)
                    });
                    return newRow;
                });
                productList.next(returnObj); // incidentList is a Subject and emits an event thats being listened to by the components
            });

        return productList;
    }




    getProductStatsByQuantityOrdered(): Observable<any> {
        return this.apiRequest.get('api/product-stats-by-quantity');
    }


  
    updateProduct(id:number, etat:number): Observable<any> {
        let bodyData : ProductUpdate= {
            "id": id,
            "etat": etat,
        };
        return this.apiRequest
            .post(`api/products/update`, bodyData)
            .map((response: Response) =>{
                console.log (response.json());
                });
    }



getProductByCode(productCode:String) : Observable<any>{
         //Create Request URL params
         let me = this;
         let params: HttpParams = new HttpParams();
         if (productCode){
            
         let productDetailSubject = new Subject<any>(); // Will use this subject to emit data that we want
         this.apiRequest.get('api/product/'+productCode)
             .subscribe(jsonResp => {
                 let returnObj = jsonResp.items.map(function(v, i, a){
                     let newRow = Object.assign({}, v, {
                     });
                     return newRow;
                 });
                 productDetailSubject.next(returnObj); // incidentList is a Subject and emits an event thats being listened to by the components
             });
         
         return productDetailSubject;
            }
            return new Observable<any>();


}



  
}
