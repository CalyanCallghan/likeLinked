import { Component, OnInit } from '@angular/core';
import { EmployeService } from 'src/app/service/employe.service';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent{

  title = 'card-view-demo';
  card_data:any[];
  constructor(private employeService:EmployeService){
    this.card_data = this.employeService.getEmployeeDetails();
  }
 

}
