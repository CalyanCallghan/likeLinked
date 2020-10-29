import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/service/notification.service';
import { UnReadService } from 'src/app/service/unreadCount.service';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { Subject } from 'rxjs';
import { ViewEncapsulation } from '@angular/core';



@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
  encapsulation: ViewEncapsulation.None
})
export class HeaderComponent implements OnInit {

  unreadCount = 0;
  public readonly destroy$: Subject<void> = new Subject<void>();
  notificationColor = 'warn';

  constructor(private notificationService: NotificationService, private unReadService: UnReadService) { }

  ngOnInit(): void {
    this.something();
    this.unReadService.getCount()
      .pipe(takeUntil(this.destroy$))
      .subscribe((count) => {
        this.unreadCount = count;
      });
  }
  something() {
    this.notificationService.getUnreadCount().subscribe(data => {
      this.unReadService.setcanCount(data);
    });
  }

  changeColor() {
    this.notificationColor = '';
  }

}
