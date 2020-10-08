import { Employee } from './../employee';
import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  public dataSource = new MatTableDataSource<Employee>();
  constructor(private employeeService: EmployeeService) { }

  ngOnInit(): void {
  }
  public getAllOwners = () => {
    this.employeeService.getEmployee(2)
    .subscribe(res => {
      this.dataSource.data = res as Employee[];
    })
  }
}
