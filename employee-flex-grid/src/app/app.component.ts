import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { EmployeService } from './employe.service';
import { PostComponent } from './post/post.component';
import { UploadImageComponent } from './upload-profile-image/upload-image.component';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'card-view-demo';
  card_data:any[];
  constructor(private employeService:EmployeService,public dialog: MatDialog){
    this.card_data = this.employeService.getEmployeeDetails();
  }
  openDialog() {
    this.dialog.open(UploadImageComponent);
  }
  openPostDialog() {
    this.dialog.open(PostComponent);
  }
}
