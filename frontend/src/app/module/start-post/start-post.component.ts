import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { PostData } from 'src/app/model/postData';
import { CreatePostComponent } from '../create-post/create-post.component';



@Component({
  selector: 'app-start-post',
  templateUrl: './start-post.component.html',
  styleUrls: ['./start-post.component.css']
})
export class StartPostComponent implements OnInit {
  postData: PostData = new PostData();
  @Output() addNewPost = new EventEmitter<PostData>();
  type:string = localStorage.getItem("type");
  constructor(public dialog: MatDialog) { }

  ngOnInit(): void {

  }

  openPostDialog() {
    let dialogRef = this.dialog.open(CreatePostComponent, {
      data: { postData: this.postData }
    });

    dialogRef.afterClosed().subscribe(result => {
      let length = Object.keys(result).length;
      this.postData = result;
      if(length != 0){
        if(this.type == this.postData.type)
          this.addNewPost.emit(result);
        }
    });
  }

}
