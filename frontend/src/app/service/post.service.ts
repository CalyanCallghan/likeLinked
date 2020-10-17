import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = 'http://localhost:8080/uploadFile123';

  constructor(private http: HttpClient) { }
  createPost(post: any,file:any): Observable<Object> {
    const uploadImageData = new FormData();
    uploadImageData.append('file', file);
    uploadImageData.append('data', JSON.stringify(post));
    console.log("post------->"+ JSON.stringify(post));
     return this.http.post(`${this.baseUrl}`,uploadImageData);
   }
}
