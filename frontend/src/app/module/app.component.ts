import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

   constructor() { }
   ngOnInit(): void {
    localStorage.setItem("employeeCode","007");
    localStorage.setItem("designation","SE");
    localStorage.setItem("designationId","1");
   }

}


