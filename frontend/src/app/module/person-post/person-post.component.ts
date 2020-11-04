
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
  value = 0;
  min: number = 0;
  max: number = 100;
  count: number;
  showForm: boolean = false;
  canShow: boolean = false;
  CommentData: CommentData[];
  commentLikesCount: number;
  postLikeCount: number;
  commentCount: number;
  tmp: number = 0;
  htmlYouWantToAdd;


  commentsForm = new FormGroup({
    comment: new FormControl('', [Validators.required])
  });

  constructor(private personPostService: PersonPostService) { }

  ngOnInit(): void {
    console.log();

    this.count = 0;
    this.getLikesCount();
    this.getCountOfCommentsByPostId();
  }
  addHTML() {
    this.htmlYouWantToAdd = "<b>Some HTML you want to display</b>";
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

  getLikesCount() {
    this.personPostService.getLikesCount().subscribe(data => {
      this.postLikeCount = data;
      console.log("all likesCount data---->" + JSON.stringify(this.postLikeCount));
    });
  }

  getCountOfCommentsByPostId() {
    this.personPostService.getCountOfCommentsByPostId().subscribe(data => {
      this.commentCount = data;
      console.log("all CommentCount data---->" + JSON.stringify(this.commentCount));
    });
  }


  showCommentsForm(postId: number) {
    this.tmp = postId;
    this.canShow = true;
    console.log("showCommentsForm------>" + postId);

    this.showForm = true;
    this.personPostService.getCommentsByPostId(postId).subscribe(data => {
      this.CommentData = data;
      console.log("commentsByPostId------>" + JSON.stringify(this.CommentData));

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

  countLike() {
    this.likeModel.isLiked = true;
    this.likeModel.empId = '10120049';
    this.personPostService.likesCount(this.likeModel).subscribe(data => {
      this.likeModel = data;
      console.log(this.likeModel);
    }, error => console.log(error));
  }

  onSubmit(postId: number) {
    this.comment.empId = '10120049';
    console.log(this.comment.content);
    this.personPostService.doComment(this.comment, postId).subscribe(data => {
      console.log(data);
    });
    this.comment.content = '';
  }

}



