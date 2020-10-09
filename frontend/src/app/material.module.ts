import { NgModule } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { A11yModule } from '@angular/cdk/a11y';
import { ClipboardModule } from '@angular/cdk/clipboard';
import { PortalModule } from '@angular/cdk/portal';
import { MatTableModule } from '@angular/material/table';
import {MatIconModule} from '@angular/material/icon';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import {MatMenuModule} from '@angular/material/menu';



const modules = [
  MatCardModule,
  MatInputModule,
  MatButtonModule,
  A11yModule,
  ClipboardModule,
  MatTableModule,
  PortalModule,
  MatListModule,
  MatSidenavModule,
  MatToolbarModule,
  MatIconModule,
  MatMenuModule
];

@NgModule({
  imports: modules,
  exports: modules,
})
export class MaterialModule { }
