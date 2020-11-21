import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { NgxSpinnerService } from 'ngx-spinner';
import { EventModel } from 'src/app/admin-data-models/event';
import { AdminArchEventsService } from 'src/app/services/admin/admin-arch-events/admin-arch-events.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-admin-archived-events',
  templateUrl: './admin-archived-events.component.html',
  styleUrls: ['./admin-archived-events.component.css']
})
export class AdminArchivedEventsComponent implements OnInit {

  constructor(private eventService: AdminArchEventsService,private dialog: MatDialog, private spinner:NgxSpinnerService) { }

  ngOnInit() {
    this.getArchievedEvents();
  }
  eventModel : EventModel[] ;
  backendUrl = environment.baseApplicationUrl;

  getArchievedEvents(){
    this.spinner.show()
    this.eventService.getArchievedEvents().subscribe(data=>{   
      this.spinner.hide()
      this.eventModel = data;
      console.log("ArchievedfromDB----->"+JSON.stringify(this.eventModel)); 
    }, error=>{
      this.spinner.hide()
      console.log(error);
    });
  }

  // openDialog(archievedModel : EventModel) {
  //   archievedModel.imagePath = "";

  //   const dialogRef = this.dialog.open(AdminPreviewEventsComponent, {
  //     data: {
  //       message: archievedModel,
  //       buttonText: {
  //         ok: 'okay',
  //         // cancel: 'cancel'
  //       }
  //     }
  //   });
  // }

}
