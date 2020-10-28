import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeeData } from '../model/employeeData';
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
  getEmployeeDetailsByDesignation(): Observable<EmployeeData> {
    return this.http.get<EmployeeData>(environment.baseApplicationUrl+"/myGroup/tl");
  }

}
