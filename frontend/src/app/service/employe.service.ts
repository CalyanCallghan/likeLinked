import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EmployeesData } from '../model/employeesData';
import { environment } from '../model/environment';
import { UserData } from '../model/userData';

@Injectable({
  providedIn: 'root'
})
export class EmployeService {

  constructor(private http: HttpClient) { }

  getEmployeeDetailsByDesignation(desigId:any): Observable<EmployeesData> {
    return this.http.get<EmployeesData>(environment.baseApplicationUrl+"/myGroup/"+desigId);
  }
  getEmployeeData(emailId:any): Observable<UserData> {
    return this.http.get<UserData>(environment.baseApplicationUrl+"/user/userDetails/"+emailId);
  }
}
