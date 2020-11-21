import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { Group } from 'src/app/admin-data-models/group';
import { AdminGroupsService } from 'src/app/services/admin/admin-groups/admin-groups.service';
// import { GroupBoxComponent } from '../group-box/group-box.component';

@Component({
  selector: 'app-admin-groups',
  templateUrl: './admin-groups.component.html',
  styleUrls: ['./admin-groups.component.css']
})
export class AdminGroupsComponent implements OnInit {


  en_cs_group = false
  groupTables = true
  c_grp_text = ''
  grp_table_data = []
  table_show = false
  usr_grp_enable = false
  all_users = []
  grp_users = []

  // @ViewChild('childComponent') childComponentRef: GroupBoxComponent;

  constructor(private adminGroupService: AdminGroupsService, private formBuilder: FormBuilder, private snackBar: MatSnackBar, private tstr: ToastrService, private spinner: NgxSpinnerService) { }
  input = false;
  groupList = [];
  group: Group = new Group();
  flag: boolean;
  clickedGroupName = '';
  clickedGroupId: number = 0;
  grp_filter_text = ''

  ngOnInit(): void {
    this.get_groups()
  }

  /* kreddy */
  create_group() {
    this.en_cs_group = true
  }
  cancel_group() {
    this.en_cs_group = false
  }
  save_group() {
    if (!this.c_grp_text) {
      this.tstr.warning('Please enter group name')
    } else {
      this.spinner.show()
      this.adminGroupService.postByGroup({ groupName: this.c_grp_text }).subscribe(data => {
        this.c_grp_text = ''
        this.cancel_group()
        this.spinner.hide()
        console.log(data);
        this.get_groups()
        this.tstr.success('Group added succesfully')
      }, error => {
        this.c_grp_text = ''
        this.spinner.hide()
        this.tstr.error('Error while adding group')
        console.log(error);
      });
    }
  }

  in_act_grps(inp) {
    this.groupList.forEach(function (ech) {
      ech['active'] = inp
    })
  }

  plus_table(id) {
    this.groupTables = false
    this.table_show = false
    this.usr_grp_enable = true
    this.cancel_group()
    this.getAllusers()
    this.getGrpusers(id)
  }

  get_tables(data) {
    this.clickedGroupName = data['groupName'];
    this.clickedGroupId = data['id'];
    this.in_act_grps(false)
    data['active'] = true
    this.table_show = true
    this.get_group_table(data['id']);
  }

  get_groups() {
    this.spinner.show()
    this.adminGroupService.getAllGroups().subscribe(data => {
      this.spinner.hide()
      console.log("getAllGroups--->", data);
      this.groupList = data;
    }, error => {
      this.spinner.hide()
      this.tstr.error('Error while fetching groups')
      console.log(error);
    });
  }
  del_tab_data(grpId) {
    this.adminGroupService.deleteByGroup(grpId).subscribe(data => {
      console.log("data--->"+data);
      this.tstr.success(this.clickedGroupName + " deleted successfully22222222222222222222");
    }, error => {
      this.spinner.hide()
      this.tstr.error('1111111111111111111')
      console.log(error);
    });
  }

  get_group_table(gid: number) {
    this.grp_table_data = []
    this.adminGroupService.getUserDetailsByGroup(gid).subscribe(
      (data) => {
        this.grp_table_data = data;
        console.log("------>" + this.grp_table_data);

      },
      (error) => {
        console.log(error);
        this.tstr.error('Error while fetching group tables')
      }
    );
  }

  deleteGroup(grpId) {

    this.adminGroupService.deleteByGroup(grpId).subscribe(data => {
      console.log("::-->check"+data)
      this.tstr.success('Group deleted successfully')
      this.get_groups()
    },
      (error) => {
        console.log(error);
        if(error.error.text == "Group deleted"){ 
          this.tstr.success('Group deleted successfully')
          this.get_groups();
        }else{
          this.tstr.error('Error while deleting group') 
        }
      }
    );
  }

  getAllusers() {
    this.all_users = []
    this.spinner.show()
    this.adminGroupService.getAllusers().subscribe(data => {
      this.spinner.hide()
      console.log("getAllUsers--->", data);
      this.all_users = data
    }, error => {
      this.spinner.hide()
      this.tstr.error('Error while fetching master users')
      console.log(error);
    });
  }

  getGrpusers(id) {
    this.all_users = []
    this.spinner.show()
    this.adminGroupService.getGrpusers(id).subscribe(data => {
      this.spinner.hide()
      console.log("getGrpUsers--->", data);
      this.grp_users = data
    }, error => {
      this.spinner.hide()
      this.tstr.error('Error while fetching group users')
      console.log(error);
    });
  }

  getCheckedMusers() {
    var ret = []
    var _this = this
    this.all_users.forEach(function (ech) {
      if (ech['status'] && ((ech['groupId']==0)))
        ret.push(ech['empId'])
    })
    return ret
  }

  getCheckedGusers() {
    var ret = []
    this.grp_users.forEach(function (ech) {
      if (ech['status'])
        ret.push(ech['emp_id'])
    })
    return ret
  }

  add_mastergroup(id) {
    this.spinner.show()
    var empids = this.getCheckedMusers()
    if (!empids.length) {
      this.tstr.warning('No employee selected')
    } else {
      var send_data = { 'groupId': id, 'empids': empids }
      var formData = new FormData()
      formData.append('userRequestList', JSON.stringify(send_data))
      this.adminGroupService.updateGrpusers(formData).subscribe(data => {
        this.spinner.hide()
        this.tstr.success('Data added successfully')
        this.plus_table(id)
      }, error => {
        this.spinner.hide()
        this.tstr.error('Error while adding users')
        console.log(error);
      });
    }
  }

  del_grpUsers(id){
    this.spinner.show()
    var empids = this.getCheckedGusers()
    if (!empids.length) {
      this.tstr.warning('No employee selected')
    } else {
      var send_data = { 'groupId': 0, 'empids': empids }
      var formData = new FormData()
      formData.append('userRequestList', JSON.stringify(send_data))
      this.adminGroupService.removeGrpusers(formData).subscribe(data => {
        this.spinner.hide()
        this.tstr.success('Users removed successfully')
        this.plus_table(id)
      }, error => {
        this.spinner.hide()
        this.tstr.error('Error while removing users')
        console.log(error);
      });
    }
  }

  /* kreddy */

}
