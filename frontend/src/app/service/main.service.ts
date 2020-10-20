import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Chat } from '../model/chat';
import { ResponseData } from '../model/response-data';

@Injectable({
  providedIn: 'root'
})
export class MainService {
  private baseUrl = 'http://localhost:8080/getMsgs';

  constructor(private http: HttpClient) { }


  createPost(post: any,file:any): Observable<ResponseData> {
    this.baseUrl =  'http://localhost:8080/uploadFile123';
    const uploadImageData = new FormData();
    uploadImageData.append('file', file);
    uploadImageData.append('data', JSON.stringify(post));
    console.log("post------->"+ JSON.stringify(post));
    return this.http.post<ResponseData>(`${this.baseUrl}`,uploadImageData);
  }

  postMsg(chat:any):Observable<Chat>{
    this.baseUrl =  'http://localhost:8080/postMsgs';
    return this.http.post<Chat>(`${this.baseUrl}`,chat);
  }
  getAllMsg(chat:any):Observable<Chat>{
    this.baseUrl =  'http://localhost:8080/getMsgs';
    return this.http.get<Chat>(`${this.baseUrl}/${chat}`);
  }

}
