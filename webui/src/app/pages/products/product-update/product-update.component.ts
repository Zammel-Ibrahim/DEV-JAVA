import { Component, AfterViewInit , ViewEncapsulation } from '@angular/core';
import { ProductService } from '../../../services/api/product.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {MatCardModule,MatInputModule} from '@angular/material/material';
import { FormBuilder, FormGroup , FormControl,Validators} from '@angular/forms';



//import { MatCardModule, MatInputModule } from '@angular/material';

@Component({
  selector: 'app-product-update',
  templateUrl: './product-update.component.html',
  styleUrls: ['./product-update.component.scss'],
  encapsulation: ViewEncapsulation.Emulated
})
export class ProductUpdateComponent implements AfterViewInit  {

  public productId:number; 
   
  public frmOrderDetail: FormGroup;
  public productDetailsRec:any={id:'',orderLine:[]};



  constructor( private route: ActivatedRoute,
    private router: Router,
    private productService: ProductService) { }

  productName : String='';
  
  productCost : String='';
  
  productCategory : String='';
  
  productState : String='';
  

  ngAfterViewInit() {
    this.getData();
  }
  

  getData(){
    var me = this;
    this.route.params
    .switchMap( function(params: Params){
        me.productId = params['id'];
        return me.productService.getProductByCode(params['id'])
    })
    .subscribe(function(resp){
        console.log("Order details", resp[0]);
            
          if (resp[0]){
            console.info('**********init form ********************');
            this.productName=resp[0].productName;
            this.productCost=resp[0].standardCost;
            this.productCategory=resp[0].category;
            this.productState=resp[0].discontinued;
                
         
            console.info('productName is '+this.productName);
          }
         
       
    });
}



  updateProduct(){
//do some stuff here 
         //alert("first material forms  "+this.productName);
         console.info('init form '+this.productName);
  }









}
