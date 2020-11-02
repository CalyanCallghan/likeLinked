import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { PostData } from 'src/app/model/postData';
import { PersonPostService } from 'src/app/service/person.service';
import { StartPostComponent } from '../start-post/start-post.component';


@Component({
  selector: 'app-my-groups',
  templateUrl: './my-groups.component.html',
  styleUrls: ['./my-groups.component.css']
})
export class MyGroupsComponent implements OnInit {
  indexId: number;
  allPosts:PostData[];
  constructor(public dialog: MatDialog, private router: Router,private personPostService: PersonPostService) {
  }
  ngOnInit(): void {
    localStorage.setItem("type","M");
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
  list = [
    {
      'id': 1,
      'value': 'Williams Kavin'
    },
    {
      'id': 2,
      'value': 'Peterson'
    },
    {
      'id': 3,
      'value': 'Stonies'
    },
    {
      'id': 4,
      'value': 'Jorden'
    },
    {
      'id': 5,
      'value': 'Barney Stinson'
    },
    {
      'id': 6,
      'value': 'Roonie'
    },
    {
      'id': 7,
      'value': 'David Thomos '
    },
    {
      'id': 8,
      'value': 'Jhon Wick'
    },
    {
      'id': 9,
      'value': 'Usain bolt'
    },
    {
      'id': 10,
      'value': 'Warner Devid'
    }
  ];
  kalyan() {
    this.router.navigate(["/chat"]);
  }

  getMyGroupPost(){
    let type = localStorage.getItem("type");
    this.personPostService.getAllPosts(type).subscribe(data => {
      this.allPosts = data;
      console.log(JSON.stringify(this.allPosts));
      console.log("data::"+this.allPosts[0].fileName);
    });
  }  

}
