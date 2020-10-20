import { NotificationComponent } from './notification/notification.component';
import { AppComponent } from './app.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MyGroupsComponent } from './my-groups/my-groups.component';
import { HomepageComponent } from './homepage/homepage.component';
import { ChatComponent } from './chat/chat.component';

const routes: Routes = [
  { path: '', component: HomepageComponent },
  { path: 'mygroup', component: MyGroupsComponent },
  { path: 'chat' , component: ChatComponent},
  { path: 'notification' , component: NotificationComponent}
  
]; 

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
