
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeesData } from 'src/app/model/employeesData';
import { EmployeService } from 'src/app/service/employe.service';
import { GroupsService } from 'src/app/service/groups.service';
import { environment } from 'src/environments/environment';

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
  designationId: string = localStorage.getItem("designationId").toLowerCase();
  designation: string = localStorage.getItem("designation").toLowerCase();
  canShowButton: boolean = true;
  isShown: boolean = false ;
  groupName:string = "SOFTWARE ENGINEER";
  selected: any;
  constructor(private groupService: GroupsService,
    private router: Router, private employeService: EmployeService) {
  }

  ngOnInit(): void {
    this.groupService.getGroupByDesignation().subscribe(result => {
      this.groupList = result;
    }, error => console.log(error));
    this.employeService.getEmployeeDetailsByDesignation(this.designationId).subscribe(result => {
      this.card_data = result;
      console.log("---card_data---------->"+JSON.stringify(result));
    }, error => console.log(error));
  }

  showMeEmplyeeList(desig: any,desigDesc: any) {
    this.groupName = desigDesc;
    this.isShown = true;
    this.employeService.getEmployeeDetailsByDesignation(desig).subscribe(result => {
      this.card_data = result;
    }, error => console.log(error));
    this.designationId = localStorage.getItem("designationId").toLowerCase();
     if (desig != this.designationId) {
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