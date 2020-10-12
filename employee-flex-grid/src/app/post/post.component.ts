import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.css']
})
export class PostComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  accept = 'image/*'
  name = 'Angular';
  fileToUpload: any;
  imageUrl: any;
  handleFileInput(file: FileList) {
    this.fileToUpload = file.item(0);

    //Show image preview
    let reader = new FileReader();
    reader.onload = (event: any) => {
      this.imageUrl = event.target.result;
    }
    reader.readAsDataURL(this.fileToUpload);
  }
  onClick() {
    const fileUpload = document.getElementById('fileUpload') as HTMLInputElement;
    fileUpload.click();
  }
  onClickVideo(){
    const fileUploadVideo = document.getElementById('fileUploadVideo') as HTMLInputElement;
    fileUploadVideo.click();
  }
  onClickAttachment(){
    const fileUploadVideo = document.getElementById('fileUploadAttachment') as HTMLInputElement;
    fileUploadVideo.click();
  }

}
