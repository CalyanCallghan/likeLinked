import { Employee } from './employee';
import { Component, OnInit } from '@angular/core';
import { EmployeeService } from './employee.service';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
   title = 'material';
   displayedColumns: string[] = ['id', 'firstName', 'lastName', 'emailId'];
   dataSource = ELEMENT_DATA;
   employees: Observable<Employee[]>;
   constructor(private employeeService: EmployeeService) { }
   ngOnInit(): void {
     this.reloadData();
     //this.dataSource = this.employees;
   }
   reloadData() {
     this.employees = this.employeeService.getEmployeesList();
   }
  
}
const ELEMENT_DATA: Employee[] = [
  {id: 1, firstName: 'Kalyan', lastName: 'Kodapaka', emailId: 'kalkodap@gmail.com'},
  {id: 2, firstName: 'Jhon', lastName: 'Wick', emailId: 'jhonwick@gmail.com'},
  {id: 3, firstName: 'Lily', lastName: 'Aldron', emailId: 'lillyaldron@gmail.com'},
  {id: 4, firstName: 'Marshal', lastName: 'Erikson', emailId: 'marshalerikson@gmail.com'},
  {id: 5, firstName: 'Ted', lastName: 'mosbey', emailId: 'tedmosbey@gmail.com'},
];
