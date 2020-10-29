import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { environment } from 'src/app/model/environment';
import { UploadImageComponent } from '../upload-profile-image/upload-image.component';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  backendUrl = environment.baseApplicationUrl;
  url:string = "jhonwick.jpg";
  constructor(private dialog: MatDialog) { }
  
  ngOnInit(): void {

  }
  openUploadDialog() {
    this.dialog.open(UploadImageComponent);
    let dialogRef = this.dialog.open(UploadImageComponent, {
      data: `Are you sure you want to upload?`
    })
    dialogRef.afterClosed().subscribe(res => {
      console.log(res.data)
    })
  }

}
