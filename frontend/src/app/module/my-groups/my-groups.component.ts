import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { EmployeesData } from 'src/app/model/employeesData';
import { PostData } from 'src/app/model/postData';
import { EmployeService } from 'src/app/service/employe.service';
import { PersonPostService } from 'src/app/service/person.service';
import { StartPostComponent } from '../start-post/start-post.component';


@Component({
  selector: 'app-my-groups',
  templateUrl: './my-groups.component.html',
  styleUrls: ['./my-groups.component.css']
})
export class MyGroupsComponent implements OnInit {
  indexId: number;
  postData:PostData[];
  card_data:EmployeesData[];
  designation:string = localStorage.getItem("designation").toLowerCase();;
  constructor(public dialog: MatDialog, private router: Router,
    private personPostService: PersonPostService, private employeService: EmployeService) {

  }
  
  ngOnInit(): void {
    localStorage.setItem("type","M");
    this.getAllPosts();
    this.employeService.getEmployeeDetailsByDesignation(this.designation).subscribe(result => {
      this.card_data = result;
    },error => console.log(error));
  }
 
  openPostDialog() {
    this.dialog.open(StartPostComponent);
  }
  openChatBox(i: number) {
    this.indexId = i;
  }
  callClose() {
    this.indexId = -1;
  }
  kalyan() {
    this.router.navigate(["/chat"]);
  }


  getAllPosts(){
    this.personPostService.getAllPosts("M").subscribe(data => {
      this.postData = data;
    });
  } 

  addNewPostItem(event:PostData){
    this.postData.unshift(event);
  }


  commentCountOfPost(countOfComment:number,indexId:number){
    this.postData[indexId].commentCount = countOfComment;
     
  }

  likeCountOfPost(countOfLike:number,indexId:number){
    this.postData[indexId].likeCount = countOfLike;
  }

}
