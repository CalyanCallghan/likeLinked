import { Component, OnInit, ChangeDetectionStrategy } from '@angular/core';
import { FixedSizeVirtualScrollStrategy, VIRTUAL_SCROLL_STRATEGY } from '@angular/cdk/scrolling';
import { HostListener } from '@angular/core';
import { NotificationService } from 'src/app/service/notification.service';
import { UnReadService } from 'src/app/service/unreadCount.service';
import { environment } from 'src/environments/environment';


export class CustomVirtualScrollStrategy extends FixedSizeVirtualScrollStrategy {
  constructor() {
    super(50, 250, 500);
  }
}

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [{ provide: VIRTUAL_SCROLL_STRATEGY, useClass: CustomVirtualScrollStrategy }]
})
export class NotificationComponent implements OnInit {

  notifications: any;
  totalCount = 0;
  unreadCount = 0;
  backEndUrl =environment.baseApplicationUrl;

  @HostListener('mouseover') onHover() {

  }

  constructor(private notificationService: NotificationService, private unReadService: UnReadService) { }

  ngOnInit(): void {
    this.getTotalNotificationCount();
    this.getUnreadNotificationCount();
    this.getAllNotifications();
  }
  makeMeUnread(id:any){
    this.notificationService.updateUnread(id,'read').subscribe(data => {
      console.log("data-->"+data);
      this.getTotalNotificationCount();
      this.getUnreadNotificationCount();
    });
  }

  getAllNotifications(){
    this.notificationService.getNotifications().subscribe(data => {
      this.notifications = data;
    });
  }

  getUnreadNotificationCount(){
    this.notificationService.getUnreadCount().subscribe(data => {
      this.unreadCount = data;
      this.unReadService.setcanCount(data);
    });
  }

  getTotalNotificationCount(){
    this.notificationService.getTotalCount().subscribe(data => {
      this.totalCount = data;
    });
  }




}
