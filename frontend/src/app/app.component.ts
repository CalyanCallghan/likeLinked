import { Employee } from './employee';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { StartPostComponent } from './start-post/start-post.component';
import { PostService } from './post.service';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

   constructor(private postService: PostService,public dialog: MatDialog) { }
   ngOnInit(): void {
    
   }
   
   openPostDialog() {
     console.log("-----dialog-----");
    this.dialog.open(StartPostComponent);
  }
  
}

