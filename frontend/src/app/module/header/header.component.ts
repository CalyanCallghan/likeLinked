import { Component, OnInit } from '@angular/core';
import { NotificationService } from 'src/app/service/notification.service';
import { UnReadService } from 'src/app/service/unreadCount.service';
import { takeUntil } from 'rxjs/internal/operators/takeUntil';
import { Observable, Subject } from 'rxjs';
import { ViewEncapsulation } from '@angular/core';
import { FormControl } from '@angular/forms';
import { SearchService } from 'src/app/service/search.service';
import { startWith, map } from 'rxjs/operators';



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

  constructor(private notificationService: NotificationService, private unReadService: UnReadService
    ,private searchService : SearchService) { }

  ngOnInit(): void {
    this.something();
    this.unReadService.getCount()
      .pipe(takeUntil(this.destroy$))
      .subscribe((count) => {
        this.unreadCount = count;
      });
      this.searchService.getAllUserDetails().subscribe(data => {
        this.userlist = data;
       console.log("fromDB--->"+JSON.stringify(this.userlist));
       
     });
 
       this.searchCtrl = new FormControl();
       this.filteredName = this.searchCtrl.valueChanges
       .pipe(startWith(''),map( element => element ? this.filteredname(element) : this.userlist.slice() ) );
  }
  something() {
    this.notificationService.getUnreadCount().subscribe(data => {
      this.unReadService.setcanCount(data);
    });
  }

  changeColor() {
    this.notificationColor = '';
  }
  
  filteredname(firstName: string) {
    console.log("userInput---->"+firstName);
    return this.userlist.filter(element => 
      element.firstName.toLowerCase().indexOf(firstName.toLowerCase()) === 0);
  }
  clearValue(){
   this.ngOnInit();
  }

}

export class UserDetails {
  constructor(public email: string,public firstName:string,public lastName:string) {}
}
