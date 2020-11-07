
import { Component, Input, OnInit } from '@angular/core';
import { FixedSizeVirtualScrollStrategy } from '@angular/cdk/scrolling';


import { VIRTUAL_SCROLL_STRATEGY } from '@angular/cdk/scrolling';
import { PersonPostService } from 'src/app/service/person.service';
import { environment } from 'src/app/model/environment';
import { PostData } from 'src/app/model/postData';
import { CommentModel } from 'src/app/model/comment';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CommentLike } from 'src/app/model/commentLike';
import { LikeModel } from 'src/app/model/like';
import { CommentData } from 'src/app/model/commentData';
import { HomepageComponent } from '../homepage/homepage.component';
import { MyGroupsComponent } from '../my-groups/my-groups.component';


export class CustomVirtualScrollStrategy extends FixedSizeVirtualScrollStrategy {
  constructor() {
    super(50, 250, 500);
  }

}

@Component({
  selector: 'app-person-post',
  templateUrl: './person-post.component.html',
  styleUrls: ['./person-post.component.css'],
  providers: [{ provide: VIRTUAL_SCROLL_STRATEGY, useClass: CustomVirtualScrollStrategy }]
})
export class PersonPostComponent implements OnInit {
  comment: CommentModel = new CommentModel();
  likeModel: LikeModel = new LikeModel();
  commentLike: CommentLike = new CommentLike();
  backendUrl = environment.baseApplicationUrl;
  canShowComment: boolean = false;
  @Input() postData: PostData[];
  pageVariable: number = 1;
  min: number = 0;
  max: number = 100;
  showForm: boolean = false;
  canShow: boolean = false;
  commentData: CommentData[];
  commentLikesCount: number;
  postLikeCount: number;
  commentCount: number;
  tmp: number = 0;
  commentsForm = new FormGroup({
    comment: new FormControl('', [Validators.required])
  });

  constructor(private personPostService: PersonPostService) { }

  ngOnInit(): void {
    console.log("all post data-----in person post->"+this.postData);
  }
 
  someFucn(newVal: any) {
    this.pageVariable = newVal;
  }

  afterLoadComplete(pdf: any) {
    this.max = pdf.numPages;
  }

  showComment() {
    this.canShowComment = true;
  }

  showCommentsForm(postId: number) {
    this.tmp = postId;
    this.canShow = true;
    console.log("showCommentsForm------>" + postId);
    this.showForm = true;
    this.personPostService.getCommentsByPostId(postId).subscribe(data => {
      this.commentData = data;
      this.commentData.reverse();
      console.log("commentsByPostId------>" + JSON.stringify(this.commentData));
    });
    this.personPostService.getCommentLikesByCommentId().subscribe(data => {
      this.commentLikesCount = data;
      console.log("commentsLikesCountByCommentId------>" + JSON.stringify(this.commentLikesCount));

    });
  }

  specificCommentLike(commentId: number) {
    console.log("specificCommentLike----->" + commentId);
    this.commentLike.empId = '10120049';
    this.commentLike.isLiked = true;
    this.commentLike.commmentId = commentId;
    this.personPostService.setSpecificCommentLike(this.commentLike).subscribe(data => {
      console.log(data);
      

    });
  }

  countLike(postId:number,index:number)  {
    let empId =  Number(localStorage.getItem("employeeCode"));
    this.personPostService.likesCount(empId,postId).subscribe(data => {
      console.log(data);
      this.postData[index].likeCount=data;
    }, error => console.log(error));
  }

  onSubmit(postId: number,index:number) {
    this.comment.empId = localStorage.getItem("employeeCode");
    console.log(this.comment.content);
    this.personPostService.doComment(this.comment, postId).subscribe(data => {
      console.log(data);
      this.showCommentsForm(postId);
      this.personPostService.getCountOfCommentsByPostId(postId).subscribe(data => {
        this.commentCount = data;
        console.log("all CommentCount data---->" +this.commentCount);
        this.postData[index].commentCount=data;
      });
    });
    this.comment.content = '';
  }

}



