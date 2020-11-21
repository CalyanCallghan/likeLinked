import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminHomeRoutingModule } from './admin-home-routing.module';
import { AdminHomeComponent } from 'src/app/components/admin/admin-home/admin-home.component';
import { AdminGroupsComponent } from '../../../components/admin/admin-groups/admin-groups.component';
import { AdminWelcomeComponent } from '../../../components/admin/admin-welcome/admin-welcome.component';
import { AdminAddEventsComponent } from '../../../components/admin/admin-add-events/admin-add-events.component';
import { AdminLiveEventsComponent } from '../../../components/admin/admin-live-events/admin-live-events.component';
import { AdminArchivedEventsComponent } from '../../../components/admin/admin-archived-events/admin-archived-events.component';
import { AdminUpdSheetComponent } from '../../../components/admin/admin-upd-sheet/admin-upd-sheet.component';
import { AdminViewSheetComponent } from '../../../components/admin/admin-view-sheet/admin-view-sheet.component';
import { AdminQueuEventsComponent } from '../../../components/admin/admin-queu-events/admin-queu-events.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminMaterialModule } from 'src/app/_helpers/material/admin-material/admin-material.module';


@NgModule({
  declarations: [AdminHomeComponent, AdminGroupsComponent, AdminWelcomeComponent, AdminAddEventsComponent, AdminLiveEventsComponent, AdminArchivedEventsComponent, AdminUpdSheetComponent, AdminViewSheetComponent, AdminQueuEventsComponent],
  imports: [
    CommonModule,
    AdminMaterialModule,
    AdminHomeRoutingModule,
    Ng2SearchPipeModule,
    FormsModule,
    ReactiveFormsModule
  ]
})
export class AdminHomeModule { }
