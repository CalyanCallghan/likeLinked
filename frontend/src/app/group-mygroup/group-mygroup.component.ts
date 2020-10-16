import { Component, OnInit,ViewChild } from '@angular/core';
import {CdkScrollable} from '@angular/cdk/scrolling';


@Component({
  selector: 'app-group-mygroup',
  templateUrl: './group-mygroup.component.html',
  styleUrls: ['./group-mygroup.component.css']
})
export class GroupMygroupComponent implements OnInit {
  @ViewChild(CdkScrollable) scrollable: CdkScrollable;

  constructor() { }

  ngOnInit(): void {
  }

}
