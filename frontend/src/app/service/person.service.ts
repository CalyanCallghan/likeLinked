import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment } from '../model/comment';
import { environment } from '../model/environment';

@Injectable({
    providedIn: 'root'
})
export class PersonPostService {
    baseUrl: string;
    constructor(private http: HttpClient) { }
    doComment(commentModel: any): Observable<Comment> {
        this.baseUrl = environment.baseApplicationUrl+"/comment/userComment";
        return this.http.post<Comment>(`${this.baseUrl}`, commentModel);
    }
}
