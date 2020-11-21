import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { CommentModel } from '../model/comment';
import { CommentData } from '../model/commentData';
import { CommentLike } from '../model/commentLike';
import { LikeModel } from '../model/like';
import { PostData } from '../model/postData';
import { SubCommentModel } from '../model/subComment';

@Injectable({
    providedIn: 'root'
})
export class PersonPostService {
    private baseUrl = environment.baseApplicationUrl+"/comment";

    constructor(private http: HttpClient) { }

    getAllPosts(type: string): Observable<PostData[]> {
        return this.http.get<PostData[]>(environment.baseApplicationUrl + "/file/getAllPostsWithCommentsAndLikes/" + type);
    }
    
    commentToPost(commentModel: CommentModel, postId: number): Observable<CommentModel> {
        console.log("from service--->" + JSON.stringify(commentModel));
        return this.http.post<CommentModel>(`${this.baseUrl}` + "/postComment/" + postId, commentModel);
    }

    getCommentsByPostId(postId: number): Observable<CommentData[]> {
        return this.http.get<CommentData[]>(`${this.baseUrl}` + "/allCommentsOfPost/" + postId);
    }

    postLikesCount(empId: number, postId: number): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}` + "/postLikes/" + empId + "/" + postId);
    }

    postCommentsCount(postId: number): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}` + "/commentsCountOfPost/" + postId);
    }

    postSubComment(commentModel: CommentModel, commentId: number): Observable<SubCommentModel> {
        console.log("from service--->" + JSON.stringify(commentModel));
        return this.http.post<SubCommentModel>(`${this.baseUrl}` + "/postSubcomment/" + commentId, commentModel);
    }

    getSubCommentsCommentsByCommentId(commentId: number): Observable<CommentData[]> {
        return this.http.get<CommentData[]>(`${this.baseUrl}` + "/allSubCommentsOfComment/" + commentId);
    }

    commentLikesCount(empId: number, commentId: number): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}` + "/commentLikes/" + empId + "/" + commentId);
    }

    subCommentCount(commentId: number): Observable<any> {
        return this.http.get<any>(`${this.baseUrl}` + "/subCommentsCountOfPost/" + commentId);
    }

    subCommentLikesCount(empId: number, subCommentId: number): Observable<number> {
        return this.http.get<number>(`${this.baseUrl}` + "/subcommentLikes/" + empId + "/" + subCommentId);
    }
}
