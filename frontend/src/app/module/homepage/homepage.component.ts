import { PersonPostComponent } from './../person-post/person-post.component';
import { Component, IterableDiffers, OnInit } from '@angular/core';
import { PersonPostService } from 'src/app/service/person.service';
import { PostData } from 'src/app/model/postData';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.css']
})
export class HomepageComponent implements OnInit {
  postData:PostData[];

  constructor(private personPostService: PersonPostService){

  }

  ngOnInit(): void {
    localStorage.setItem("type","A");
    this.getAllPosts();
  }

  getAllPosts(){
    this.personPostService.getAllPosts("A").subscribe(data => {
      console.log("----data---"+JSON.stringify(data));
      this.postData = data;
      console.log("--post data-"+JSON.stringify(this.postData));
    });
  } 
  addNewPostItem(event:PostData){
    this.postData.unshift(event);
  }
  
  commentCountOfPost(countOfComment:number,indexId:number){
    alert(countOfComment+"-----------"+indexId);
    this.postData[indexId].commentCount = countOfComment;
  }

  likeCountOfPost(countOfLike:number,indexId:number){
    alert(countOfLike+"-----------"+indexId);
    console.log(JSON.stringify(this.postData))
    this.postData[indexId].likeCount = countOfLike;
  }

}
