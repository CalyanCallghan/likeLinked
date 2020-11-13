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
    private baseUrl = environment.baseApplicationUrl + "/comment";

    constructor(private http: HttpClient) { }

     getAllPosts(type: string): Observable<PostData[]> {
         return this.http.get<PostData[]>(environment.baseApplicationUrl + "/file/getAllPostsWithCommentsAndLikes/" + type);
     }

    commentToPost(commentModel: CommentModel, postId: number): Observable<CommentModel> {
        console.log("from service--->" + JSON.stringify(commentModel));
        return this.http.post<CommentModel>(`${this.baseUrl}` + "/posts/" + postId + "/comments", commentModel);
    }

    postLikesCount(empId: number, postId: number): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}` + "/posts/" + empId + "/" + postId + "/likes");
    }

    postCommentsCount(postId: number): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}` + "/posts/" + postId + "/commentCount");
    }

    getCommentsByPostId(postId: number): Observable<CommentData[]> {
        return this.http.get<CommentData[]>(`${this.baseUrl}` + "/posts/" + postId + "/comments");
    }

    setSpecificCommentLike(commentLike: CommentLike): Observable<CommentLike> {
        return this.http.post<CommentLike>(`${this.baseUrl}` + "/Comment/" + commentLike.commmentId + "/likes", commentLike);
    }

    getCommentLikesByCommentId(): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}` + "/Comment/1/commentLikesCount");
    }
}
