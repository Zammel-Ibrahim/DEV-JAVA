import {NgModule} from "@angular/core";
import { CommonModule } from '@angular/common';
import {MatCardModule,MatInputModule,MatProgressSpinnerModule} from '@angular/material';



@NgModule({
imports: [MatCardModule,MatInputModule,MatProgressSpinnerModule],
exports: [MatCardModule,MatInputModule,MatProgressSpinnerModule],
})
export class CustomMaterialModule { }
