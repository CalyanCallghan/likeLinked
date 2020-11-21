import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NotificationsModel } from '../model/notification';
import { UserData } from '../model/userData';

@Injectable({
    providedIn: 'root'
})
export class SearchService {

    constructor(private http: HttpClient) { }
  
    getAllUserDetails(): Observable<UserData[]> {
      return this.http.get<UserData[]>(environment.baseApplicationUrl+"/user/getAllUsers");
    }

    displayLatestTenEvents(): Observable<NotificationsModel> {
      return this.http.get<NotificationsModel>(environment.baseApplicationUrl+"/event/displayTenEvents");
    }
  }