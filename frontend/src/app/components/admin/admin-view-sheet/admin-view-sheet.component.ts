import { Component, OnInit } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { ToastrService } from 'ngx-toastr';
import { send } from 'process';
import { AdminViewSheetService } from 'src/app/services/admin/admin-view-sheet/admin-view-sheet.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-admin-view-sheet',
  templateUrl: './admin-view-sheet.component.html',
  styleUrls: ['./admin-view-sheet.component.css']
})
export class AdminViewSheetComponent implements OnInit {

  view_filter_text = ''
  org_table_data = []
  tableData = []
  constructor(private aview_service: AdminViewSheetService, private tstr: ToastrService, private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
    this.getSheetData();
  }

  getSheetData() {
    this.spinner.show()
    this.aview_service.getSheetData().subscribe((res) => {
      this.spinner.hide()
      this.org_table_data = JSON.parse(JSON.stringify(res))
      this.tableData = res
      console.log('response--', res)
    }, (error) => {
      this.spinner.hide()
      console.error('error', error)
    });
  }

  downloadURI(uri) {
    var link = document.createElement("a");
    link.href = uri;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  dowSheetData() {
    this.downloadURI(environment.baseApplicationUrl + '/excel/download')
  }

  post_sheet_update(inp_data, index) {
    var copy_data = JSON.parse(JSON.stringify(inp_data))
    var send_data =
    {
      empId: copy_data['empId'],
      firstName: copy_data['firstName'],
      lastName: copy_data['lastName'],
      email: copy_data['email'],
      phoneNo: copy_data['phoneNo'],
    }
    if (Object.values(send_data).includes("")) {
      this.tstr.warning('Please Insert all fields')
    } else {
      inp_data['editable'] = false
      this.spinner.show()
      this.aview_service.updSheetRow(send_data).subscribe((res) => {
        this.spinner.hide()
        this.tstr.success('record updated Successfully')
        console.log('response--', res)
      }, (error) => {
        this.spinner.hide()
        this.tstr.error('Error in record update')
        this.cancel_sheet_update(index)
        console.error('error', error)
      });
    }
  }

  delSheetRow(empId: number) {
    this.spinner.show()
    this.aview_service.delSheetRow(empId).subscribe((res) => {
      this.spinner.hide()
      this.tstr.success('record deleted Successfully')
      console.log('response--', res)
      this.getSheetData();
    }, (error) => {
      this.spinner.hide()
      this.tstr.error('Error in record update')
      console.error('error', error)
      this.getSheetData();
    });
  }

  cancel_sheet_update(index) {
    this.tableData[index] = JSON.parse(JSON.stringify(this.org_table_data[index]))
  }

}
