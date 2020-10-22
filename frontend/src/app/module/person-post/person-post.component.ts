
import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/model/post';
import { FixedSizeVirtualScrollStrategy } from '@angular/cdk/scrolling';


import { VIRTUAL_SCROLL_STRATEGY } from '@angular/cdk/scrolling';
import { ChangeDetectionStrategy } from '@angular/core';
import { PersonPostService } from 'src/app/service/person.service';
import { Comment } from 'src/app/model/comment';


export class CustomVirtualScrollStrategy extends FixedSizeVirtualScrollStrategy {
  constructor() {
    super(50, 250, 500);
  }
}

@Component({
  selector: 'app-person-post',
  templateUrl: './person-post.component.html',
  styleUrls: ['./person-post.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  providers: [{ provide: VIRTUAL_SCROLL_STRATEGY, useClass: CustomVirtualScrollStrategy }]
})
export class PersonPostComponent implements OnInit {
  comment: Comment = new Comment();
  canShowComment:boolean = false;

  constructor(private personPostService: PersonPostService) { }

  ngOnInit(): void {
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

}



