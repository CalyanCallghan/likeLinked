import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';

/* add evnets , live , queued , archived ,  preview */
import { MatDialogModule } from '@angular/material/dialog';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import {MatDividerModule} from '@angular/material/divider';
import {MatFormFieldModule} from '@angular/material/form-field';

const adminMaterialModules = [
  MatDialogModule,
  MatSnackBarModule,
  MatDividerModule,
  MatFormFieldModule, 
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    adminMaterialModules
  ],
  exports: [
    adminMaterialModules
  ],
})
export class AdminMaterialModule { }

