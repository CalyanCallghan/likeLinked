import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { StartPostComponent } from '../start-post/start-post.component';


@Component({
  selector: 'app-my-groups',
  templateUrl: './my-groups.component.html',
  styleUrls: ['./my-groups.component.css']
})
export class MyGroupsComponent implements OnInit {
  index:number;
  constructor(public dialog: MatDialog,private router: Router,


    ) {
   }
  ngOnInit(): void { 
  }
  openPostDialog() {
    this.dialog.open(StartPostComponent);
 }
 list = [
  {
    'id': 1,
    'value': 'PavanKalyan Kodapaka'
  },
  {
    'id': 2,
    'value': 'Sunitha'
  },
  {
    'id': 3,
    'value': 'Manish'
  },
  {
    'id': 4,
    'value': 'Alam'
  },
  {
    'id': 5,
    'value': 'Preethi'
  },
  {
    'id': 6,
    'value': 'Swarna'
  },
  {
    'id': 7,
    'value': 'Sai'
  },
  {
    'id': 8,
    'value': 'Kalyan'
  },
  {
    'id': 9,
    'value': 'Chandra'
  },
  {
    'id': 10,
    'value': 'Mallik'
  }
];
kalyan(){
  this.router.navigate(["/chat"]);
//   alert("-----kalyan-----");
// this.index = 0;
}

}
