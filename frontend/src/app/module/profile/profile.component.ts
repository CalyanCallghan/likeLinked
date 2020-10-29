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
  constructor(private dialog: MatDialog) { }
  
  ngOnInit(): void {

  }
  openUploadDialog() {
    this.dialog.open(UploadImageComponent);
  }

}
