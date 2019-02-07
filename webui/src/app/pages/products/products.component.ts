import { Component, OnInit,TemplateRef, ViewChild } from '@angular/core';
import { ProductService } from '../../services/api/product.service';
import { Router } from '@angular/router';

@Component({
	selector: 's-products-pg',
	templateUrl: './products.component.html',
    styleUrls: [ './products.scss'],
})

export class ProductsComponent implements OnInit {

    @ViewChild('productDiscontinuedTpl') productDiscontinuedTpl: TemplateRef<any>;
     
    @ViewChild('productIdUpdate') productIdUpdate: TemplateRef<any>;

    //ngx-Datatable Variables
    columns:any[];
    rows:any[];

    constructor( private router: Router, private productService: ProductService) {}
    ngOnInit() {
        var me = this;
        me.getPolicyData();
        this.columns=[
            {prop:"productCode"  , name: "Code"         , width:60 , cellTemplate: this.productIdUpdate   },
            {prop:"productName"  , name: "Name"         , width:200 },
            {prop:"standardCost" , name: "Standard Cost", width:100 },
            {prop:"category"     , name: "Category"     , width:100 },
            {prop:"targetLevel"  , name: "Target Level" , width:100 },
            {prop:"reorderLevel" , name: "Reorder Level", width:100 },
            {prop:"minimumReorderQuantity", name: "Min Order", width:100 },
            {prop:"discontinued" , name: "activate" , width:90, cellTemplate: this.productDiscontinuedTpl}
        ];

    }

    getPolicyData() {
        this.productService.getProducts().subscribe( (policyData) => {
            this.rows = policyData;
        });
    }


    updateProduct(e ) {

        var codeProduct=e.target.id;
        var etat=0;
        var num : number;
        num=601;
        if(e.target.checked){
              etat=1;
        }
      
        this.productService.updateProduct(num,etat)
            .subscribe(response=> {
                console.log(response);
                })
        console.log('update eneded susceffully'); 
     }
}
