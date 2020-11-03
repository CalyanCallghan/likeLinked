import { CreatePost } from '../../model/createPost';
import { Component, Inject, NgZone, OnInit,ViewChild } from '@angular/core';
import { ResponseData } from 'src/app/model/response-data';
import { PostService } from 'src/app/service/post.service';
import { PersonPostService } from 'src/app/service/person.service';
import { PostData } from 'src/app/model/postData';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { SearchService } from 'src/app/service/search.service';
import { Observable} from 'rxjs';
import { FormControl, Validators } from '@angular/forms';
import { startWith, map } from 'rxjs/operators';
import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import {take} from 'rxjs/operators';



@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrls: ['./create-post.component.css']
})
export class CreatePostComponent implements OnInit {
  accept = 'image/*'
  name = 'Angular';
  format: string="none";
  allPosts: any;
  url: any;
  pageVariable: number = 1;
  value = 0;
  min: number = 0;
  max: number = 100;
  file: any;
  post: CreatePost = new CreatePost();
  postData: PostData = new PostData();
  searchTerm: String;
  length: number;
  searchCtrl: FormControl;
  filteredName: Observable<any[]>;
  userData: any[] = [];
  responseData: ResponseData = new ResponseData();
  userList: any[] = [];
  dataaaa:any;
  hashTagCount:number =0;
  textareaPost = new FormControl('', [Validators.required]);
  selected = 'A';
  @ViewChild('autosize') autosize: CdkTextareaAutosize;
  constructor(private mainService: PostService, private personPostService: PersonPostService,
    public dialogRef: MatDialogRef<CreatePostComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any, public snackBar: MatSnackBar,private searchService : SearchService,private _ngZone: NgZone) { }


  ngOnInit(): void {
    this.post.type='A';
    this.searchService.getAllUserDetails().subscribe(data => {
      this.userData = data;
    });
    this.searchCtrl = new FormControl();
    this.filteredName = this.searchCtrl.valueChanges
      .pipe(startWith(''), map(element => element ? this.filteredname(element) : this.userData.slice()));
  }
  triggerResize() {
    this._ngZone.onStable.pipe(take(1))
        .subscribe(() => this.autosize.resizeToFitContent(true));
  }
  filteredname(firstName: string) {
    return this.userData.filter(element => 
      element.firstName.toLowerCase().indexOf(firstName.toLowerCase()) === 0);
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
    let hashTagsContent = "";
    for (let i = 0; i < this.hashTagCount; i++) {      
      hashTagsContent = hashTagsContent+" #"+(<HTMLInputElement>document.getElementById("name"+i)).value;
    }    
    this.post.createdBy = Number(localStorage.getItem("employeeCode"));;
    this.post.groupId = Number(localStorage.getItem("designationId"));
    this.post.format = this.format;
    this.post.content = this.post.content+hashTagsContent;
    this.mainService.createPost(this.post, this.file)
      .subscribe(data => {
        this.responseData = data;
        console.log(JSON.stringify(this.responseData));
        if (this.responseData.status == "OK") {
          this.dialogRef.close(this.responseData.postDetails);
          this.snackBar.open(this.responseData.message, "X", { duration: 5000 });
        }
      },error => {
        if(error.error.status=="EXPECTATION_FAILED" && error.error.message=="Fail to upload files!!!") {
          this.snackBar.open("Please try again", "X", {duration:5000});
        } else {
          this.snackBar.open("Sorry file is not uploaded Successfully!", "X", {duration:5000});
        }
        console.log(error);
      });
  }
  getAllPosts() {
    let type = localStorage.getItem("type");
    this.personPostService.getAllPosts(type).subscribe(data => {
      this.allPosts = data;
    });
  }

  add() {
    this.hashTagCount++;
    this.userList.push({});    
  }
  callSomeFunction(data:any){
    
  }
}
