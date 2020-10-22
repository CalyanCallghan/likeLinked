import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
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
  getMyGroupByTL(): Observable<any> {
    return this.http.get(environment.baseApplicationUrl+"/myGroup/tl");
  }
  getMyGroupByPM(): Observable<any> {
    return this.http.get(environment.baseApplicationUrl+"/myGroup/pm");
  }
  getMyGroupBySE(): Observable<any> {
    return this.http.get(environment.baseApplicationUrl+"/myGroup/se");
  }
  getMyGroupBySSE(): Observable<any> {
    return this.http.get(environment.baseApplicationUrl+"/myGroup/sse");
  }
  getMyGroupByAccounts(): Observable<any> {
    return this.http.get(environment.baseApplicationUrl+"/myGroup/accounts");
  }
}
