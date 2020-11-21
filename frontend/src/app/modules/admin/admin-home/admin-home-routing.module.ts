import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AdminAddEventsComponent } from 'src/app/components/admin/admin-add-events/admin-add-events.component';
import { AdminArchivedEventsComponent } from 'src/app/components/admin/admin-archived-events/admin-archived-events.component';
import { AdminGroupsComponent } from 'src/app/components/admin/admin-groups/admin-groups.component';
import { AdminHomeComponent } from 'src/app/components/admin/admin-home/admin-home.component';
import { AdminLiveEventsComponent } from 'src/app/components/admin/admin-live-events/admin-live-events.component';
import { AdminQueuEventsComponent } from 'src/app/components/admin/admin-queu-events/admin-queu-events.component';
import { AdminUpdSheetComponent } from 'src/app/components/admin/admin-upd-sheet/admin-upd-sheet.component';
import { AdminViewSheetComponent } from 'src/app/components/admin/admin-view-sheet/admin-view-sheet.component';
import { AdminWelcomeComponent } from 'src/app/components/admin/admin-welcome/admin-welcome.component';

const routes: Routes = [
  {
    path: '', component: AdminHomeComponent, children: [
      { path:'', component:AdminWelcomeComponent },
      { path: 'groups', component: AdminGroupsComponent },
      { path:'addevents',component:AdminAddEventsComponent },
      { path:'queuevents',component:AdminQueuEventsComponent },
      { path:'liveevents',component:AdminLiveEventsComponent },
      { path:'archevents',component:AdminArchivedEventsComponent },
      { path:'updsheet',component:AdminUpdSheetComponent },
      { path:'viewsheet',component:AdminViewSheetComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminHomeRoutingModule { }
