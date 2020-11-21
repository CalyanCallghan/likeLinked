import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { EmployeesData } from '../model/employeesData';
import { UserData } from '../model/userData';

@Injectable({
  providedIn: 'root'
})
export class EmployeService {
  baseurl  = environment.baseApplicationUrl+"/groups/"
  constructor(private http: HttpClient) { }

  getEmployeeDetailsByDesignation(desigId:any): Observable<EmployeesData[]> {
    return this.http.get<EmployeesData[]>(this.baseurl+"/groupById/"+desigId);
  }
  getEmployeeData(emailId:any): Observable<UserData> {
    return this.http.get<UserData>(environment.baseApplicationUrl+"/user/userDetails/"+emailId);
  }
}
