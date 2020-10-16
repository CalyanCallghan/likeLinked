import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  private baseUrl = 'http://localhost:8080/uploadFile';

  constructor(private http: HttpClient) { }
  createPost(file: any): Observable<Object> {
    const uploadImageData = new FormData();
    uploadImageData.append('file', file);
    return this.http.post(`${this.baseUrl}`, uploadImageData);
  }
}
