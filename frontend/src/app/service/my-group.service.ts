import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { EmployeesData } from '../model/employeesData';

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
