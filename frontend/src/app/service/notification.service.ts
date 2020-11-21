import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { NotificationsModel } from '../model/notification';




@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private baseUrl = environment.baseApplicationUrl+"/event";

  constructor(private http: HttpClient) { }

  getNotifications(): Observable<NotificationsModel[]> {
    return this.http.get<NotificationsModel[]>(`${this.baseUrl}/getAllEvents`);
  }

  getTotalCount(): Observable<any> {
    return this.http.get(`${this.baseUrl}/totalCount`);
  }

  getUnreadCount(): Observable<any> {
    return this.http.get(`${this.baseUrl}/unreadCount`);
  }

  updateUnread(id: number,status:any): Observable<Object> {
    const data = new FormData();
    data.append('status', status);
    return this.http.put(`${this.baseUrl}/notificationUnread/${id}`,data);
  }
}
