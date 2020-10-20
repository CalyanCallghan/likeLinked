import { Component, OnInit, ViewChild } from '@angular/core';
import { Chat } from 'src/app/model/chat';
import { MainService } from 'src/app/service/main.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  message:string;
  constructor(private mainService: MainService) { }
  chat:Chat = new Chat();
  chatResponse : Chat = new Chat();
  chatData : Chat[]; 
  allData:any[] = [
    {"from":"kalyan","content":"Hi"},
    {"from":"kalyan","content":"How are you?"},
    {"from":"preethi","content":"Hi"},
    {"from":"kalyan","content":"Same here"}]
  ngOnInit(): void {
    this.getAllMsgs();

  }
  sendMsg(){
    this.chat.empId=7;
    this.chat.from='kalyan';
    this.chat.to='preethi';
    this.mainService.postMsg(this.chat).subscribe(data => {
      this.chatResponse = data;
      console.log(JSON.stringify(this.chatResponse));
    },error => console.log(error));
  }

  getAllMsgs(){
    this.chat.empId=7;
    this.mainService.getAllMsg(this.chat.empId).subscribe(data => {
      this.chatResponse = data;
      console.log("---->"+JSON.stringify(this.chatData));
    },error => console.log(error));
  }
}
