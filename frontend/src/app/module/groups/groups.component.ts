import { Component, OnInit} from '@angular/core';
import { Router } from '@angular/router';
import { environment } from 'src/app/model/environment';
import { EmployeService } from 'src/app/service/employe.service';
import { GroupsService } from 'src/app/service/groups.service';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {
  groupList: any;
  card_data:any;
  backendUrl = environment.baseApplicationUrl;
  designation:string =localStorage.getItem("designation").toLowerCase();
  canShowButton:boolean = true;
  constructor(private groupService: GroupsService,
    private router: Router, private employeService: EmployeService) { }

  ngOnInit(): void {
    this.groupService.getGroupByDesignation().subscribe(result => {
      this.groupList = result;
    },error => console.log(error));
    this.employeService.getEmployeeDetailsByDesignation(this.designation).subscribe(result => {
      this.card_data = result;
    },error => console.log(error));
  }

  showMeEmplyeeList(designation: any) {
    let des = designation.toLowerCase();
    console.log("des----->"+des);
    this.employeService.getEmployeeDetailsByDesignation(des).subscribe(result => {
      this.card_data = result;
    },error => console.log(error));
    if(des != designation){
      this.canShowButton = false;
    }else{
      this.canShowButton = true;
    }
  }

}