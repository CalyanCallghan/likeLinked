import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { EventModel } from 'src/app/admin-data-models/event';
import { MatDialog } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AdminAddEventsService } from 'src/app/services/admin/admin-add-events/admin-add-events.service';
import { DatePipe } from '@angular/common';
import { NgxSpinnerService } from 'ngx-spinner';
declare var $: any;


@Component({
  selector: 'app-admin-add-events',
  templateUrl: './admin-add-events.component.html',
  styleUrls: ['./admin-add-events.component.css']
})
export class AdminAddEventsComponent implements OnInit {

  imageUrl: string | ArrayBuffer;
  pipe: any;
  currentDate: any;
  currentDateVal: any;
  targetedFile: any;

  eventForm: FormGroup;
  eventModel: EventModel = new EventModel();
  empId: string;
  validateEndDate: string;

  prev_items = {}

  constructor(private formBuilder: FormBuilder, private snackBar: MatSnackBar, private dialog: MatDialog,
    private eventService: AdminAddEventsService, private spinner: NgxSpinnerService) { }

  ngOnInit() {
    localStorage.setItem("empId", "247");

    this.pipe = new DatePipe('en-IN');
    this.currentDate = new Date();
    this.currentDateVal = this.pipe.transform(this.currentDate, 'yyyy-MM-dd');

    this.eventForm = this.formBuilder.group({
      title: ['', Validators.maxLength],
      info: ['', Validators.maxLength],
      start: ['', Validators.required],
      end: ['', Validators.required],
      image: ['', Validators.required],

    });
  }

  validateCurrentDate() {
    this.validateEndDate = this.eventForm.get('start').value;
  }

  onSelectFile(event: any) {
    this.targetedFile = event.target.files && event.target.files[0];
    if (this.targetedFile) {
      var reader = new FileReader();
      reader.readAsDataURL(this.targetedFile);
      reader.onload = (event) => {
        this.imageUrl = (<FileReader>event.target).result;
        console.log("::-->" + this.imageUrl);
      }
    }
    if (event.target.files && event.target.files.length > 0) {
      const file = (event.target.files[0] as File);
      this.eventForm.get('image').setValue(file);
    }
  }



  eventSubmit() {
    this.eventModel.title = this.eventForm.get('title').value;
    this.eventModel.info = this.eventForm.get('info').value;
    this.eventModel.eventStartDate = this.eventForm.get('start').value;
    this.eventModel.eventEndDate = this.eventForm.get('end').value;

    this.empId = localStorage.getItem("empId");
    console.log("fromUIEventModel--->" + JSON.stringify(this.eventModel));

    const formData = new FormData();
    formData.append('image', this.eventForm.get('image').value);
    formData.append('data', JSON.stringify(this.eventModel));

    if (!this.eventForm.valid) {
      this.snackBar.open("Please enter all values", "X", { duration: 5000 });
    } else {
      this.spinner.show()
      //add Event service
      this.eventService.addEvents(this.empId, formData).subscribe(data => {
        this.spinner.hide()
        this.eventModel = data;
        console.log("AfterAddedfromDB----->" + JSON.stringify(data));

        if (data['status'] == "EXPECTATION_FAILED" && data['message'] == "Fail") {
          this.snackBar.open("Event Update Failed", "X", { duration: 5000 });
        } else if (data['status'] == "EXPECTATION_FAILED" && data['message'] == "Not Admin") {
          this.snackBar.open("Failed! , please try with Admin credentials", "X", { duration: 5000 });
        } else {
          this.snackBar.open("Event has been published", "X", { duration: 5000 });
        }
        window.location.reload();

      }, error => {
        this.spinner.hide()
        if (error.error.status == "EXPECTATION_FAILED" && error.error.message == "Fail") {
          this.snackBar.open("Image Upload Failed", "X", { duration: 5000 });
        } else if (error.error.status == "EXPECTATION_FAILED" && error.error.message == "Not Admin") {
          this.snackBar.open("Failed! , please try with Admin credentials", "X", { duration: 5000 });
        }
        console.log(error);
      });
    }
  }

  openDialog() {
    this.prev_items['title'] = this.eventForm.get('title').value;
    this.prev_items['info'] = this.eventForm.get('info').value;
    this.prev_items['fname'] = this.eventForm.get('image').value.name

    $('#previewModal').modal('show')

    // const dialogRef = this.dialog.open(AdminPreviewEventsComponent,{
    //   maxHeight: '85vh',
    //   width: '70vh',
    //   data:{
    //     message: this.eventModel,
    //     buttonText: {
    //       ok: 'okay',
    //       // cancel: 'cancel'
    //     }
    //   }
    // });

  }




}
