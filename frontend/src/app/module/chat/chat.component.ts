import { Component, OnInit, ViewChild } from '@angular/core';
import { Chat } from 'src/app/model/chat';
import { PostService } from 'src/app/service/post.service';
import { GroupsComponent } from '../groups/groups.component';
import { MyGroupsComponent } from '../my-groups/my-groups.component';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  message: string;
  constructor(private mainService: PostService, private myGroupsComponent: MyGroupsComponent,private groupsComponent: GroupsComponent) { }
  chat: Chat = new Chat();
  showNotification = false;
  chatResponse: Chat = new Chat();
  accept = 'image/*'
  name = 'Angular';
  format: any;
  fileName: any;
  allPosts: any;
  url: any;
  pageVariable: number = 1;
  value = 0;
  min: number = 0;
  max: number = 100;
  file: any;

  allData: any[] = [
    { "from": "kalyan", "content": "Hi" },
    { "from": "kalyan", "content": "How are you?" },
    { "from": "preethi", "content": "Hi" },
    { "from": "kalyan", "content": "Same here" }]
  ngOnInit(): void {
    this.getAllMsgs();

  }
  closePopup() {
    this.myGroupsComponent.callClose()
    this.groupsComponent.callClose()
  }
  sendMsg() {    
    this.chat.empId = 7;
    this.chat.from = 'kalyan';
    this.chat.to = 'preethi';
    this.mainService.postMsg(this.chat).subscribe(data => {
      this.chatResponse = data;
      console.log(JSON.stringify(this.chatResponse));
    }, error => console.log(error));
  }

  getAllMsgs() {
    this.chat.empId = 7;
    this.mainService.getAllMsg(this.chat.empId).subscribe(data => {
      this.chatResponse = data;
    }, error => console.log(error));
  }

  onCloseClick() {
    this.showNotification = false;
  }

  chatFucn(newVal: any) {

    this.pageVariable = newVal;
  }
  afterLoadComplete(pdf: any) {
    this.max = pdf.numPages;

  }
  onSelectFile(event: any) {
    this.showNotification = true;
    this.file = event.target.files && event.target.files[0];

    if (this.file) {
     // this.fileName = event.target.files[0].name;       
      var reader = new FileReader();
      reader.readAsDataURL(this.file);
      if (this.file.type.indexOf('image') > -1) {
        this.fileName = event.target.files[0].name; 
        this.format = 'image';
      } else if (this.file.type.indexOf('video') > -1) {
        this.format = 'video';
        this.fileName = event.target.files[0].name; 
      } else if (this.file.type.indexOf('pdf') > -1) {
        this.format = 'pdf';
        this.fileName = event.target.files[0].name; 
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

}
