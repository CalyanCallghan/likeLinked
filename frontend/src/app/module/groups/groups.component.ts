
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeesData } from 'src/app/model/employeesData';
import { environment } from 'src/app/model/environment';
import { EmployeService } from 'src/app/service/employe.service';
import { GroupsService } from 'src/app/service/groups.service';
import { MyGroupsComponent } from '../my-groups/my-groups.component';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css'],
 
})
export class GroupsComponent implements OnInit {
  groupList: any;
  indexId: number;
  card_data: EmployeesData[];
  backendUrl = environment.baseApplicationUrl;
  designation: string = localStorage.getItem("designation").toLowerCase();
  canShowButton: boolean = true;
  isShown: boolean = false ;
  groupName:string;
  selected: any;
  constructor(private groupService: GroupsService,
    private router: Router, private employeService: EmployeService) {
  }

  ngOnInit(): void {
    this.groupName = this.designation;
    this.groupService.getGroupByDesignation().subscribe(result => {
      this.groupList = result;
    }, error => console.log(error));
    this.employeService.getEmployeeDetailsByDesignation(this.designation).subscribe(result => {
      this.card_data = result;
    }, error => console.log(error));
  }

  showMeEmplyeeList(desig: any) {
    let des = desig.toLowerCase();
    this.groupName = desig;
    this.isShown = true;
    this.employeService.getEmployeeDetailsByDesignation(des).subscribe(result => {
      this.card_data = result;
    }, error => console.log(error));
    this.designation = localStorage.getItem("designation").toLowerCase();+
    console.log(des+"---"+this.designation);
    if (des != this.designation) {
      this.canShowButton = false;
    } else {
      this.canShowButton = true;
    }
  }
  openChat1Box(i: number) {
    this.indexId = i;
  }
  
  callClose() {
    this.indexId = -1;
  }

  public hasData(): boolean {
    return (this.card_data != null && this.card_data.length > 0);
  }

  select(item: any) {
    this.selected = item;
 };
 isActive(item: any) {
   return this.selected === item;
 };

}