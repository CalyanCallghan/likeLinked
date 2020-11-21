import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { EventModel } from 'src/app/admin-data-models/event';
import { AdminLiveEventsService } from 'src/app/services/admin/admin-live-events/admin-live-events.service';
declare var $:any;

@Component({
  selector: 'app-admin-live-events',
  templateUrl: './admin-live-events.component.html',
  styleUrls: ['./admin-live-events.component.css']
})
export class AdminLiveEventsComponent implements OnInit {

  constructor(private formBuilder: FormBuilder,private snackBar: MatSnackBar,private eventService: AdminLiveEventsService,
    private dialog: MatDialog) { }

  imageUrl: string | ArrayBuffer;
  eventModel: EventModel[];
  empId: string;
  targetedFile: any;
  upd_file: File;
  flag1:boolean;
  flag:boolean;
  whichImageStatus:string;
 

  ngOnInit(){
    localStorage.setItem("empId", "247");
    this.getLiveEvents();
    this.flag1=true;
    this.flag=false;
    this.whichImageStatus="nochoosen";
  }

  // Don't delete
  getLiveEvents(){
    this.eventService.getLiveEvents().subscribe(data=>{   
      this.eventModel = data;
      console.log("LivefromDB----->"+JSON.stringify(this.eventModel)); 
    }, error=>{
      console.log(error);
    });
  }
  // Don't delete


  // <!----choose file----> 
  onSelectFile(event: any) {
    this.targetedFile = event.target.files && event.target.files[0];
    if (this.targetedFile) {
      var reader = new FileReader();
      reader.readAsDataURL(this.targetedFile);
      reader.onload = (event) => {
        this.imageUrl = (<FileReader>event.target).result;
        console.log("::-->"+this.imageUrl);
        this.whichImageStatus="choosen_file";
      }
    }
    if (event.target.files && event.target.files.length > 0) {
      const inp_file = (event.target.files[0] as File);
      this.upd_file = inp_file;
    }
  }
  // <!----choose file----> 



  // <!----edit fields----> 
  edit(index) {
    alert("You can edit now");
    this.flag1=false;
    this.flag=true;

    var ret_data = JSON.parse(JSON.stringify(this.eventModel))
    ret_data.forEach(function (ech, i) {
      if (i == index) {
        ech['edit_details'] = true
      } else {
        ech['edit_details'] = false
      }
    })
    this.eventModel = JSON.parse(JSON.stringify(ret_data))
  }
  // <!----edit fields----> 


  updateSubmit(inp_data) {

    console.log(inp_data)

    var send_data = inp_data;

    if (!this.upd_file) {
      alert('Please upload an image')
    } else {

      var formData = new FormData();
      formData.append('image', this.upd_file);
      formData.append('data', JSON.stringify(send_data));

      this.empId = localStorage.getItem("empId");
      this.eventService.updateLiveEvents(this.empId, send_data.eventId, formData).subscribe(res_data => {
        console.log("AfterUpdatefromDB----->" + JSON.stringify(res_data));
        if (res_data['status'] == "EXPECTATION_FAILED" && res_data['message'] == "Fail") {
          this.snackBar.open("Event Update Failed", "X", { duration: 5000 });
        } else if (res_data['status'] == "EXPECTATION_FAILED" && res_data['message'] == "Not Admin") {
          this.snackBar.open("Failed! , please try with Admin credentials", "X", { duration: 5000 });
        } else {
          this.snackBar.open("Event updated successfully!", "X", { duration: 5000 });
        }
         window.location.reload();

      }, error => {
        if (error.error.status == "EXPECTATION_FAILED" && error.error.message == "Fail") {
          this.snackBar.open("Image Upload Failed", "X", { duration: 5000 });
        } else if (error.error.status == "EXPECTATION_FAILED" && error.error.message == "Not Admin") {
         this.snackBar.open("Failed! , please try with Admin credentials", "X", { duration: 5000 });
        }
        console.log(error);
      });
    }

  }


  openDialog(ind:number){
    $('#viewModal_'+ind).modal('show')
  }

  // <!----View Future Event----> 
  // openDialog(liveModel : EventModel) {
 
  //   if(this.whichImageStatus == "choosen_file"){
  //     liveModel.imagePath = this.imageUrl;
  //    }

    
  // }


}
