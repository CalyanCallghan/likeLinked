import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { GroupsService } from 'src/app/service/groups.service';
import { MyGroupService } from 'src/app/service/my-group.service';

import { Grouplist } from './grouplist';

@Component({
  selector: 'app-groups',
  templateUrl: './groups.component.html',
  styleUrls: ['./groups.component.css']
})
export class GroupsComponent implements OnInit {
  bool = false;
  groupList: Grouplist = new Grouplist();
  desg_desc: any;
  list: any;
  se: any;
  sse: any;
  tl: any;
  accountant: any;
  pm: any;
  b = true;
  techLead: any;
  constructor(private groupService: GroupsService,
    private dialog: MatDialog,
    private router: Router,
    private myGroupService: MyGroupService) { }

  ngOnInit(): void {
    this.groupService.getGroupByDesignation().subscribe(result => {
      console.log(result);
      this.groupList = result;
      //console.log(this.groupList.size);

      console.log("length--->" + Object.keys(this.groupList).length);

      this.se = this.groupList[0].desg_desc;
      console.log(this.se);
      this.sse = this.groupList[1].desg_desc;
      console.log(this.sse);
      this.tl = this.groupList[2].desg_desc;
      console.log(this.tl);
      this.accountant = this.groupList[3].desg_desc;
      console.log(this.accountant);
      this.pm = this.groupList[4].desg_desc;
      console.log(this.pm);

    });
  }

  @ViewChild('secondDialog', { static: true }) secondDialog: TemplateRef<any>;
  openDialogWithTemplateRef(templateRef: TemplateRef<any>, id: number) {


    console.log("check:: " + id);

    if (this.tl === 'TL' && id == 2) {
      console.log(this.tl === 'TL');
      this.myGroupService.getMyGroupByTL().subscribe(result => {
        if (result) {
          this.list = result;
          console.log(this.list);
        }
      });
    }
    else if (this.pm === 'PM' && id == 4) {
      console.log(this.pm === 'PM');
      this.myGroupService.getMyGroupByPM().subscribe(result => {
        if (result) {
          this.list = result;
          // console.log('dialog'+this.groupList);
          console.log(this.list);
        }
      });
    }
    else if (this.se === 'SE' && id == 0) {
      this.myGroupService.getMyGroupBySE().subscribe(result => {
        if (result) {
          this.list = result;
          console.log(this.list);
          //console.log('dialog'+this.groupList);
        }
      });
    }
    else if (this.sse === 'SSE' && id == 1) {
      console.log(this.desg_desc === 'Accountant');
      this.myGroupService.getMyGroupBySSE().subscribe(result => {
        if (result) {
          this.list = result;
          console.log(this.list);
          //console.log('dialog'+this.groupList);
        }
      });
    }
    else if (this.accountant === 'Accountant' && id == 3) {
      console.log(this.desg_desc);
      console.log(this.desg_desc === 'Accountant');
      this.myGroupService.getMyGroupByAccounts().subscribe(result => {
        if (result) {
          this.list = result;
          console.log(this.list);
          //console.log('dialog'+this.groupList);
        }
      });

    }
    this.dialog.open(templateRef, {

      height: '42%',
      width: '30%',

    });

  }
  openDialogWithoutRef() {
    this.dialog.open(this.secondDialog);
  }
  myGroup() {

    this.router.navigate(["/mygroup"]);
    this.dialog.closeAll();
  }

}
