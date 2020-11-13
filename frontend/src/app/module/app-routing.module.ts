import { NotificationComponent } from './notification/notification.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MyGroupsComponent } from './my-groups/my-groups.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ChatComponent } from './chat/chat.component';
import { GroupsComponent } from './groups/groups.component';
import { LogoutComponent } from './logout/logout.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { LayoutComponent } from './layout/layout.component';
import { AuthService } from '../service/auth.service';
import { TestGridComponent } from './test-grid/test-grid.component';

// const routes: Routes = [
//   { path: '', component: HomepageComponent },
//   { path: 'mygroup', component: MyGroupsComponent },
//   { path: 'chat' , component: ChatComponent},
//   { path: 'notification' , component: NotificationComponent},
//   { path: 'groups' , component: GroupsComponent},
//   { path: 'logout' , component: LogoutComponent},
//   { path: 'employeeList/:desigId' , component: EmployeeListComponent}
// ]; 

// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })

const routes: Routes = [
  {
    path: 'logout',
    component: LogoutComponent,
  },
  {
    path:"",
    component:LayoutComponent,
    canActivate: [AuthService],
    children: [
        { path: '', component: HomepageComponent,canActivate: [AuthService] },
        { path: 'mygroup', component: MyGroupsComponent,canActivate: [AuthService]  },
        { path: 'chat' , component: ChatComponent,canActivate: [AuthService] },
        { path: 'notification' , component: NotificationComponent,canActivate: [AuthService] },
        { path: 'groups' , component: GroupsComponent,canActivate: [AuthService] }
    ]
  }
 ];
 

 export class DashboardRoutingModule { }
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
// @NgModule({
//   imports: [RouterModule.forRoot(routes)],
//   exports: [RouterModule]
// })

export class AppRoutingModule { }
