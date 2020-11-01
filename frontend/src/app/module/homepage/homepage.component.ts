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
    this.getAllPosts();
  }

  getAllPosts(){
    this.personPostService.getAllPosts("A").subscribe(data => {
      this.postData = data;
    });
  } 
  addNewPostItem(event:PostData){
    this.postData.unshift(event);
  }

}
