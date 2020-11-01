import { CreatePost } from '../../model/createPost';
import { Component, EventEmitter, Inject, OnInit } from '@angular/core';
import { ResponseData } from 'src/app/model/response-data';
import { PostService } from 'src/app/service/post.service';
import { PersonPostService } from 'src/app/service/person.service';
import { Output } from '@angular/core';
import { PostData } from 'src/app/model/postData';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';


@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  accept = 'image/*'
  name = 'Angular';
  format: any;
  allPosts: any;
  url: any;
  pageVariable: number = 1;
  value = 0;
  min: number = 0;
  max: number = 100;
  file: any;
  post: CreatePost = new CreatePost();
  postData: PostData = new PostData();
  responseData: ResponseData = new ResponseData();
  constructor(private mainService: PostService, private personPostService: PersonPostService,
    public dialogRef: MatDialogRef<CreatePostComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }


  ngOnInit(): void {
     this.postData = {
       "postId": 1,
       "fileName": "ted_mosbey.jpg",
       "postContent": "Your Google Account was just signed in to from a new Windows device. You're getting this email to make sure it was you.",
       "postFormat": "image",
       "firstName": "Ted",
       "lastName": "Mobsey",
       "userProfile": "defaultimg.png"
     }
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
    this.post.createdBy = Number(localStorage.getItem("employeeCode"));;
    this.post.groupId = Number(localStorage.getItem("designationId"));
    this.post.format = this.format;
    this.mainService.createPost(this.post, this.file)
      .subscribe(data => {
        this.responseData = data;
        console.log(JSON.stringify(this.responseData));
        if (this.responseData.status == "OK") {
          this.dialogRef.close(this.postData);
        }
      }, error => console.log(error));
  }
  getAllPosts() {
    let type = localStorage.getItem("type");
    this.personPostService.getAllPosts(type).subscribe(data => {
      this.allPosts = data;
      console.log("output---->" + JSON.stringify(this.allPosts));
    });
  }
}
