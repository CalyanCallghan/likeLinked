import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../model/environment';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private baseUrl = environment.baseApplicationUrl+"/notification";

  constructor(private http: HttpClient) { }

  getNotifications(): Observable<Notification[]> {
    return this.http.get<Notification[]>(`${this.baseUrl}/getNotifications`);
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
