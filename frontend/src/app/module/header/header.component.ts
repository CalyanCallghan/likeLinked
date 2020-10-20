import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/service/notification.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  unreadCount= 0;
  notificationColor= 'warn';


  constructor(private notificationService :NotificationService) { }

  ngOnInit(): void {
      //unread count
      this.notificationService.getUnreadCount().subscribe(data => {
        this.unreadCount = data;
      });
  }

  changeColor(){
    this.notificationColor= '';
  }

}
