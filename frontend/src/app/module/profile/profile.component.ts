import { UserData } from './../../model/userData';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { environment } from 'src/app/model/environment';
import { EmployeService } from 'src/app/service/employe.service';
import { UploadImageComponent } from '../upload-profile-image/upload-image.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  backendUrl = environment.baseApplicationUrl;
  picName:string;
  userdata:UserData= new UserData();
  userId:string = localStorage.getItem("employeeCode");
  userEmailId:string = localStorage.getItem("emailId");

  constructor(private dialog: MatDialog,private employeService:EmployeService) { }
  
  ngOnInit(): void {
    this.employeService.getEmployeeData(this.userEmailId).subscribe(data => {
      this.userdata = data;
    },error => console.log(error));
  }
  openUploadDialog() {
    let dialogRef = this.dialog.open(UploadImageComponent, {
      data: {animal: this.picName }
    });

    dialogRef.afterClosed().subscribe(result => {
      if(result !== undefined){
        this.userdata.file_name = result;
      }
    });
  }

}
