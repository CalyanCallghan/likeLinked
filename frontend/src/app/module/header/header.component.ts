import { NotificationsModel } from './../../model/notification';
import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/service/notification.service';
import { UnReadService } from 'src/app/service/unreadCount.service';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import {Observable, Subject } from 'rxjs';
import { ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchService } from 'src/app/service/search.service';
import { startWith, map } from 'rxjs/operators';
import { EmployeService } from 'src/app/service/employe.service';
import { UserData } from 'src/app/model/userData';
import { environment } from 'src/environments/environment';




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
  searchTerm: String;
  length: number;
  searchCtrl: FormControl;
  filteredName: Observable<any[]>;
  userlist: any[] = [];
  userdata:UserData= new UserData();
  userId:string = localStorage.getItem("employeeCode");
  userEmailId:string = localStorage.getItem("emailId");
  backendUrl = environment.baseApplicationUrl;
  notifications : NotificationsModel[];
  colorStatus: string;

  constructor(private notificationService: NotificationService, private unReadService: UnReadService
    ,private searchService: SearchService,private employeService:EmployeService) { }

  ngOnInit(): void {    
    this.getUnreadCount();
    this.getAllNotifications();
    // this.unReadService.getCount()
    //   .pipe(takeUntil(this.destroy$))
    //   .subscribe((count) => {
    //     this.unreadCount = count;
    //   });
    this.searchService.getAllUserDetails().subscribe(data => {
      this.userlist = data;
      console.log("fromDB--->" + JSON.stringify(this.userlist));

    });
    this.searchCtrl = new FormControl();
    this.filteredName = this.searchCtrl.valueChanges
      .pipe(startWith(''), map(element => element ? this.filteredname(element) : this.userlist.slice()));
    this.employeService.getEmployeeData(this.userEmailId).subscribe(data => {
      this.userdata = data;
    }, error => console.log(error));
  }
  getUnreadCount() {
    this.notificationService.getUnreadCount().subscribe(data => {
      this.unReadService.setcanCount(data);
    });
  }

  changeColor() {
    this.notificationColor = '';
  }

  filteredname(firstName: string) {
    console.log("userInput---->" + firstName);
    return this.userlist.filter(element =>
      element.firstName.toLowerCase().indexOf(firstName.toLowerCase()) === 0);
  }
  clearValue() {
    console.log("0----clkear----");
    this.ngOnInit();
  }
  onButtonGroupClick($event){
    let clickedElement = $event.target || $event.srcElement;
    if( clickedElement.nodeName === "BUTTON" ) {
  
      let isCertainButtonAlreadyActive = clickedElement.parentElement.querySelector(".active");
      // if a Button already has Class: .active
      if( isCertainButtonAlreadyActive ) {
        isCertainButtonAlreadyActive.classList.remove("active");
      }
  
      clickedElement.className += " active";
    }
  
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


export class UserDetails {
  constructor(public email: string, public firstName: string, public lastName: string) { }
}
