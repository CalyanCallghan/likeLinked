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
    private baseUrl = environment.baseApplicationUrl+"/comment";

    constructor(private http: HttpClient) { }

    getAllPosts(type: string): Observable<PostData[]> {
        return this.http.get<PostData[]>(environment.baseApplicationUrl + "/file/getAllPostsWithCommentsAndLikes/" + type);
    }
    likesCount(empId:number,postId:number): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}` + "/posts/"+empId+"/"+postId+"/likes");
    }

    getLikesCount(postId): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}` + "/posts/1/postLikesCount");
    }

    doComment(commentModel: CommentModel,postId:number): Observable<CommentModel> {
        console.log("from service--->" + JSON.stringify(commentModel));
        return this.http.post<CommentModel>(`${this.baseUrl}` + "/posts/"+postId+"/comments", commentModel);

    }

    setSpecificCommentLike(commentLike: CommentLike): Observable<CommentLike> {
        return this.http.post<CommentLike>(`${this.baseUrl}` + "/Comment/"+commentLike.commmentId+"/likes", commentLike);

    }

    getCommentsByPostId(postId:number): Observable<CommentData[]> {
        return this.http.get<CommentData[]>(`${this.baseUrl}` + "/posts/"+postId+"/comments");
    }

    getCountOfCommentsByPostId(postId:number): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}` + "/posts/"+postId+"/commentCount");
    }

    getCommentLikesByCommentId(): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}` + "/Comment/1/commentLikesCount");
    }
}
