import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MyGroupsComponent } from './my-groups/my-groups.component';
import { GroupMygroupComponent } from './group-mygroup/group-mygroup.component';


const routes: Routes = [
  { path: '', component: GroupMygroupComponent },
  { path: 'mygroup', component: MyGroupsComponent },
  
]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
