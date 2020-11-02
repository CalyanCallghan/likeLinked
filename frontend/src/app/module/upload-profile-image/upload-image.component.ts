import { Inject } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ImageCroppedEvent } from 'ngx-image-cropper';
import { ResponseData } from 'src/app/model/response-data';
import { PostService } from 'src/app/service/post.service';


@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})
export class UploadImageComponent implements OnInit {
  imageChangedEvent: any = '';
  croppedImage: any = '';
  photoUploaded:boolean = true;
  afterConversion:any;
  fileName:string;
  responseData : ResponseData = new ResponseData();
  fileToReturn: File;
  userId:string = localStorage.getItem("employeeCode");
  
  constructor(private postService: PostService,public dialogRef: MatDialogRef<UploadImageComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) {
      console.log("data-->"+this.data.animal);
     }

  ngOnInit(): void {

  }
  onClick() {
    const fileUpload = document.getElementById('fileUpload') as HTMLInputElement;
    fileUpload.click();
  }
  fileChangeEvent(event: any): void {
    this.imageChangedEvent = event;
    this.photoUploaded = false;
    this.fileName = event.target.files[0].name;
    this.data.animal = this.fileName ;
    console.log("fileName---->"+this.fileName);
  }
  imageCropped(event: ImageCroppedEvent) {
    this.croppedImage = event.base64;
    this.fileToReturn = this.base64ToFile(
      event.base64,
      this.fileName,
    )
  }

  uploadMyProfile(){
    this.data.animal = this.fileName ;
    console.log("data-->"+this.data.animal);
    this.data.animal = this.fileName ;
    this.postService.updateProfilePic(this.userId, this.fileToReturn)
      .subscribe(data => {
        this.responseData = data;
        console.log(JSON.stringify(this.responseData));
        if(this.responseData.status == "OK"){
          this.dialogRef.close(this.fileName);
        }
      },error => console.log(error));
  }
  base64ToFile(data, filename) {
    const arr = data.split(',');
    const mime = arr[0].match(/:(.*?);/)[1];
    const bstr = atob(arr[1]);
    let n = bstr.length;
    let u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, { type: mime });
  }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
