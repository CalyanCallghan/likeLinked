import { Component, OnInit } from '@angular/core';
import { NotificationsModel } from 'src/app/model/notification';
import { SearchService } from 'src/app/service/search.service';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.css']
})
export class NewsComponent implements OnInit {
  tenNotifications : NotificationsModel;
  constructor(private latestTenEvents : SearchService) { }

  ngOnInit(): void {
      this.latestTenEvents.displayLatestTenEvents().subscribe(data => {
        this.tenNotifications = data;
        console.log("TenEventsfromDB-->"+ JSON.stringify(this.tenNotifications));
      });
  }

}
