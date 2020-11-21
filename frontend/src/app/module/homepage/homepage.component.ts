import { PersonPostComponent } from './../person-post/person-post.component';
import { Component, IterableDiffers, OnInit } from '@angular/core';
import { PersonPostService } from 'src/app/service/person.service';
import { PostData } from 'src/app/model/postData';
import { NotificationService } from 'src/app/service/notification.service';
import { NotificationsModel } from 'src/app/model/notification';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  postData:PostData[];
  notifications : NotificationsModel[];
  colorStatus: string;

  constructor(private personPostService: PersonPostService,private notificationService: NotificationService){

  }

  ngOnInit(): void {
    localStorage.setItem("type","A");
    this.getAllPosts();
    this.getAllNotifications();
  }

  getAllPosts(){
    this.personPostService.getAllPosts("A").subscribe(data => {
      this.postData = data;
    });
  } 
  addNewPostItem(event:PostData){
    this.postData.unshift(event);
  }

  getAllNotifications(){
    this.notificationService.getNotifications().subscribe(data => {
    console.log("notification ----> ", JSON.stringify(data));
    this.notifications = data;
    console.log("notification---sdfs--> ", this.notifications[0].status);
    this.colorStatus = this.notifications[0].status;
    });
  }


}
