
import { Component, ElementRef, Input, OnInit, ViewChild } from '@angular/core';
import { FixedSizeVirtualScrollStrategy } from '@angular/cdk/scrolling';


import { VIRTUAL_SCROLL_STRATEGY } from '@angular/cdk/scrolling';
import { PersonPostService } from 'src/app/service/person.service';
import { PostData } from 'src/app/model/postData';
import { CommentModel } from 'src/app/model/comment';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CommentLike } from 'src/app/model/commentLike';
import { LikeModel } from 'src/app/model/like';
import { CommentData } from 'src/app/model/commentData';
import { HomepageComponent } from '../homepage/homepage.component';
import { MyGroupsComponent } from '../my-groups/my-groups.component';
import { environment } from 'src/environments/environment';
import { SubCommentModel } from 'src/app/model/subComment';
declare var $:any;


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

  @ViewChild("focusSubInput") focusSubInput: ElementRef;

  comment: CommentModel = new CommentModel();
  subCommentModel:SubCommentModel = new SubCommentModel();
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
    console.log("all post data-----in person post->" + this.postData);
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
    this.comment.content = '';
    this.tmp = postId;
    this.canShow = true;
    console.log("showCommentsForm------>" + postId);
    this.showForm = true;
    this.personPostService.getCommentsByPostId(postId).subscribe(data => {
      this.commentData = data;
      this.commentData.reverse();
      console.log("commentsByPostId------>" + JSON.stringify(this.commentData));
    });

  }

  getSubComments(cmntId: number, index: number) {
    console.log("comment id------>" + cmntId);
    console.log("index------>" + index);
    this.personPostService.getSubCommentsCommentsByCommentId(cmntId).subscribe(data => {
      console.log('--sub comment data is ', data)
      console.log('comment data before', this.commentData)
      this.commentData[index]['subComments'] = data
      console.log('comment data after', this.commentData)
    });
  }

  countLike(postId: number, index: number) {
    let empId = Number(localStorage.getItem("employeeCode"));
    this.personPostService.postLikesCount(empId, postId).subscribe(data => {
      console.log(data);
      this.postData[index].likeCount = data;
    }, error => console.log(error));
  }

  countChildLike(cmntId: number, index: number) {
    console.log(this.commentData)
    console.log('-----------', index)
    let empId = Number(localStorage.getItem("employeeCode"));
    this.personPostService.commentLikesCount(empId, cmntId).subscribe(data => {
      console.log(data);
      this.commentData[index].likeCount = data;
    }, error => console.log(error));
  }

  subCommentLikesCount(sub_cmntId: number, index: number, sub_index : number) {
    console.log(this.commentData)
    console.log('-----------', index)
    let empId = Number(localStorage.getItem("employeeCode"));
    this.personPostService.subCommentLikesCount(empId, sub_cmntId).subscribe(data => {
      console.log(data);
      this.commentData[index]['subComments'][sub_index].likeCount = data;
    }, error => console.log(error));
  }

  post_sub_cmnt(cmntId: number, index: number, sub_index: number){
    this.subCommentModel.empId = localStorage.getItem("employeeCode");
    console.log("cmntId---->"+cmntId+"---->",JSON.stringify(this.subCommentModel));
    var cmnt_text = $('.text-area-'+index+'-'+sub_index).val()
    if (cmnt_text) {
      this.subCommentModel.content = cmnt_text
      this.personPostService.postSubComment(this.subCommentModel, cmntId).subscribe(data => {
        console.log(data);
        this.getSubComments(cmntId, sub_index);
        this.personPostService.subCommentCount(cmntId).subscribe(data => {
          this.commentCount = data;
          console.log("all post_sub_cmnt data---->" + this.commentCount);
          this.commentData[index].commentCount = this.commentCount;
        });
      });
    }
    $('.text-area-'+index+'-'+sub_index).val("")
    this.subCommentModel.content = '';
  }

  onSubmit(postId: number, index: number) {
    this.comment.empId = localStorage.getItem("employeeCode");
    console.log(this.comment.content);
    if (this.comment.content) {
      this.personPostService.commentToPost(this.comment, postId).subscribe(data => {
        console.log(data);
        this.showCommentsForm(postId);
        this.personPostService.postCommentsCount(postId).subscribe(data => {
          this.commentCount = data;
          console.log("all CommentCount data---->" + this.commentCount);
          this.postData[index].commentCount = data;
        });
      });
    }
    this.comment.content = '';
  }

  subCmntClick(){
    console.log('')
  }


}



