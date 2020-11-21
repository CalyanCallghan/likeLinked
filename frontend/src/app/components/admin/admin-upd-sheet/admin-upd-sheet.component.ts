import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { AdminUpdSheetService } from 'src/app/services/admin/admin-upd-sheet/admin-upd-sheet.service';
import { isBuffer } from 'util';

@Component({
  selector: 'app-admin-upd-sheet',
  templateUrl: './admin-upd-sheet.component.html',
  styleUrls: ['./admin-upd-sheet.component.css']
})
export class AdminUpdSheetComponent implements OnInit {

  sheetToUpload: File = null;

  show_add_records = false
  tableData = []

  constructor(private upd_service: AdminUpdSheetService, private tstr: ToastrService, private spinner: NgxSpinnerService, private router: Router) { }

  ngOnInit(): void {
  }

  uploadSheet(files: FileList) {
    this.sheetToUpload = files.item(0);
  }

  cnl_upd_sheet() {
    this.sheetToUpload = null
  }

  post_upd_sheet() {
    const formdata = new FormData()
    formdata.append('file', this.sheetToUpload)
    console.log(formdata)
    this.spinner.show()
    this.upd_service.uploadSheet(formdata).subscribe((res) => {
      this.spinner.hide()
      this.tstr.success('File Uploaded Successfully')
      this.router.navigate(['admin/groups']);
      console.log('response--', res)
    }, (error) => {
      this.spinner.hide()
      this.tstr.error('Error in File uploading')
      console.error('error', error)
    });
  }

  clearTabledata() {
    this.tableData = []
  }

  disable_add_records() {
    this.show_add_records = false
    this.clearTabledata()
  }

  enable_add_records() {
    this.show_add_records = true
    var data = {
      "firstName": "",
      "lastName": "",
      "group_id": 0,
      "empId": 1,
      "email": "",
      "phoneNo": ""
    }
    this.tableData.push(data)
  }

  records_validation() {
    var ret = false
    console.log(this.tableData)
    if (this.tableData.length) {
      this.tableData.forEach(function (ech) {
        if (!ret) {       
          if (Object.values(ech).includes("")) {
            ret = true
          }
        }
      })
    }
    return ret
  }

  save_records() {
    var send_data = JSON.parse(JSON.stringify(this.tableData))
    var valid = this.records_validation()
    if (!send_data.length) {
      this.tstr.warning('please add atleast one record')
    }
    else if (valid) {
      this.tstr.warning('Please Insert all fields')
    }
    else {
      this.spinner.show()
      this.upd_service.save_records(send_data).subscribe((res) => {
        this.spinner.hide()
        this.tstr.success('records added Successfully')
        this.router.navigate(['admin/viewsheet'])
        console.log('response--', res)
      }, (error) => {
        this.spinner.hide()
        this.tstr.error('Error in records addition')
        console.error('error', error)
      });
    }
  }

}
