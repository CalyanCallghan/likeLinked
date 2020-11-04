import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentModel } from '../model/comment';
import { CommentData } from '../model/commentData';
import { CommentLike } from '../model/commentLike';
import { environment } from '../model/environment';
import { LikeModel } from '../model/like';
import { PostData } from '../model/postData';

@Injectable({
    providedIn: 'root'
})
export class PersonPostService {
    baseUrl: string;
    constructor(private http: HttpClient) { }

    getAllPosts(type: string): Observable<PostData[]> {
        return this.http.get<PostData[]>(environment.baseApplicationUrl + "/file/getAllPosts/" + type);
    }
    likesCount(likeModel: LikeModel): Observable<LikeModel> {
        this.baseUrl = 'http://localhost:8080/comment';
        return this.http.post<LikeModel>(`${this.baseUrl}` + "/posts/1/likes", likeModel);
    }

    getLikesCount(): Observable<any> {
        this.baseUrl = 'http://localhost:8080/comment';
        return this.http.get<any>(`${this.baseUrl}` + "/posts/1/postLikesCount");
    }


    doComment(commentModel: CommentModel,postId:number): Observable<CommentModel> {
        console.log("from service--->" + JSON.stringify(commentModel));
        this.baseUrl = 'http://localhost:8080/comment';
        return this.http.post<CommentModel>(`${this.baseUrl}` + "/posts/"+postId+"/comments", commentModel);

    }

    setSpecificCommentLike(commentLike: CommentLike): Observable<CommentLike> {
        this.baseUrl = 'http://localhost:8080/comment';
        return this.http.post<CommentLike>(`${this.baseUrl}` + "/Comment/"+commentLike.commmentId+"/likes", commentLike);

    }

    getCommentsByPostId(postId:number): Observable<CommentData[]> {
        this.baseUrl = 'http://localhost:8080/comment';
        return this.http.get<CommentData[]>(`${this.baseUrl}` + "/posts/"+postId+"/comments");
    }

    getCountOfCommentsByPostId(): Observable<any> {
        this.baseUrl = 'http://localhost:8080/comment';
        return this.http.get<any>(`${this.baseUrl}` + "/posts/1/commentCount");
    }

    getCommentLikesByCommentId(): Observable<any> {
        this.baseUrl = 'http://localhost:8080/comment';
        return this.http.get<any>(`${this.baseUrl}` + "/Comment/1/commentLikesCount");
    }
}
