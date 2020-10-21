import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MyGroupService {
  private listGroup = "http://localhost:8080/myGroup/group"; // URL to web myGroup
  httpOptions = {
    headers: new HttpHeaders(),
    withCredentials: true
  };
  constructor(private http: HttpClient) { }
  getMyGroupByTL(): Observable<any> {
    return this.http.get(`http://localhost:8080/myGroup/tl`);
  }
  getMyGroupByPM(): Observable<any> {
    return this.http.get(`http://localhost:8080/myGroup/pm`);
  }
  getMyGroupBySE(): Observable<any> {
    return this.http.get(`http://localhost:8080/myGroup/se`);
  }
  getMyGroupBySSE(): Observable<any> {
    return this.http.get(`http://localhost:8080/myGroup/sse`);
  }
  getMyGroupByAccounts(): Observable<any> {
    return this.http.get(`http://localhost:8080/myGroup/accounts`);
  }
}
