import { Post } from './../../model/post';
import { Component, OnInit } from '@angular/core';
import { ResponseData } from 'src/app/model/response-data';
import { PostService } from 'src/app/service/post.service';


@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  accept = 'image/*'
  name = 'Angular';
  format: any;
  url: any;
  pageVariable: number = 1;
  value = 0;
  min: number = 0;
  max: number = 100;
  file: any;
  post:Post = new Post();
  responseData : ResponseData = new ResponseData();
  constructor(private mainService: PostService) { }

  ngOnInit(): void { 
    
  }

  someFucn(newVal: any) {
    this.pageVariable = newVal;
  }
  afterLoadComplete(pdf: any) {
    this.max = pdf.numPages;

  }
  onSelectFile(event: any) {
    this.file = event.target.files && event.target.files[0];
    if (this.file) {
      var reader = new FileReader();
      reader.readAsDataURL(this.file);
      if (this.file.type.indexOf('image') > -1) {
        this.format = 'image';
      } else if (this.file.type.indexOf('video') > -1) {
        this.format = 'video';
      } else if (this.file.type.indexOf('pdf') > -1) {
        this.format = 'pdf';
      }
      reader.onload = (event) => {
        this.url = (<FileReader>event.target).result;
      }
    }
  }
  onClick() {
    const fileUpload = document.getElementById('fileUpload') as HTMLInputElement;
    fileUpload.click();
  }
  onClickVideo() {
    const fileUploadVideo = document.getElementById('fileUploadVideo') as HTMLInputElement;
    fileUploadVideo.click();
  }
  onClickAttachment() {
    const fileUploadVideo = document.getElementById('fileUploadAttachment') as HTMLInputElement;
    fileUploadVideo.click();
  }
  createPost() {
    this.post.email='pavan.kalyan@onpassive.com'
    this.mainService.createPost(this.post,this.file)
      .subscribe(data => {
        this.responseData = data;
        console.log(JSON.stringify(this.responseData));
        if(this.responseData.status == "OK"){
          const fileUpload = document.getElementById('cancel') as HTMLInputElement;
          fileUpload.click();
        }
      },error => console.log(error));
  }
}
