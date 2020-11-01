
import { Component, Input, OnInit } from '@angular/core';
import { CreatePost } from 'src/app/model/createPost';
import { FixedSizeVirtualScrollStrategy } from '@angular/cdk/scrolling';


import { VIRTUAL_SCROLL_STRATEGY } from '@angular/cdk/scrolling';
import { ChangeDetectionStrategy } from '@angular/core';
import { PersonPostService } from 'src/app/service/person.service';
import { Comment } from 'src/app/model/comment';
import { environment } from 'src/app/model/environment';
import { PostData } from 'src/app/model/postData';


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
  comment: Comment = new Comment();
  backendUrl = environment.baseApplicationUrl;
  canShowComment:boolean = false;
  @Input() postData: PostData[];
  pageVariable: number = 1;
  value = 0;
  min: number = 0;
  max: number = 100;

  constructor(private personPostService: PersonPostService) { }

  ngOnInit(): void {
    //this.getAllPosts();
  }

  postComment() {
    this.personPostService.doComment(this.comment).subscribe(data => {
      console.log(JSON.stringify(this.comment));
      //this.canShowComment = false;
      this.comment.comment = "";
    }, error => console.log(error));
  }
  
  showComment(){
    this.canShowComment = true;
  }
  someFucn(newVal: any) {
    this.pageVariable = newVal;
  }

  
  afterLoadComplete(pdf: any) {
       this.max = pdf.numPages;
  }

  /*getAllPosts(){
    let type = localStorage.getItem("type");  
    console.log("type--->"+type);
    this.personPostService.getAllPosts(type).subscribe(data => {
      this.allPosts = data;
      console.log("all post data---->"+JSON.stringify(this.allPosts));
    });
  } */

}



