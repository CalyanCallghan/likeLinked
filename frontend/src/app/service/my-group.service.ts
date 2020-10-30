import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeesData } from '../model/employeesData';
import { environment } from '../model/environment';

@Injectable({
  providedIn: 'root'
})
export class MyGroupService {
  httpOptions = {
    headers: new HttpHeaders(),
    withCredentials: true
  };
  constructor(private http: HttpClient) { }
  getEmployeeDetailsByDesignation(): Observable<EmployeesData> {
    return this.http.get<EmployeesData>(environment.baseApplicationUrl+"/myGroup/tl");
  }

}
