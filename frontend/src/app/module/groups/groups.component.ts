import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EmployeeData } from 'src/app/model/employeeData';
import { EmployeService } from 'src/app/service/employe.service';
import { GroupsService } from 'src/app/service/groups.service';
import { MyGroupService } from 'src/app/service/my-group.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {
  groupList: any;
  card_data:any;

  constructor(private groupService: GroupsService,
    private router: Router, private employeService: EmployeService) { }

  ngOnInit(): void {
    this.groupService.getGroupByDesignation().subscribe(result => {
      this.groupList = result;
    },error => console.log(error));

    let designationId = localStorage.getItem("designation").toLowerCase();
    this.employeService.getEmployeeDetailsByDesignation(designationId).subscribe(result => {
      this.card_data = result;
    },error => console.log(error));
  }

  showMeEmplyeeList(designation: any) {
    let des = designation.toLowerCase();
    console.log("des----->"+des);
    this.employeService.getEmployeeDetailsByDesignation(des).subscribe(result => {
      this.card_data = result;
    },error => console.log(error));
  }

}