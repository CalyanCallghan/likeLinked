import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Chat } from '../model/chat';
import { ResponseData } from '../model/response-data';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = environment.baseApplicationUrl+"/file";

  constructor(private http: HttpClient) { }

  createPost(post: any,file:any): Observable<ResponseData> {
    console.log("createPost---->");
    const uploadImageData = new FormData();
    uploadImageData.append('files', file);
    uploadImageData.append('data', JSON.stringify(post));
    return this.http.post<ResponseData>(`${this.baseUrl}/uploadFile`,uploadImageData);
  }

  updateProfilePic(userId: any,file:any): Observable<ResponseData> {
    const data = new FormData();
    data.append('file', file);
    data.append('userId', userId);
    return this.http.post<ResponseData>(`${environment.baseApplicationUrl}/user/uploadProfileImage`,data);
  }

  postMsg(chat:any):Observable<Chat>{
    return this.http.post<Chat>(`${this.baseUrl}/postMsgs`,chat);
  }
  getAllMsg(chat:any):Observable<Chat>{
    return this.http.get<Chat>(`${this.baseUrl}/getMsgs/${chat}`);
  }
}
