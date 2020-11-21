import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css']
})
export class AdminHomeComponent implements OnInit {

  tabs = [
    {
      name: "Groups",
      path: '/admin/groups'
    },
    {
      name: "Events",
      path: '',
      child:[
        {
          name: 'Add Events',
          path:'/admin/addevents',
        },
        {
          name: 'Queued Events',
          path:'/admin/queuevents'
        },
        {
          name: 'Live Events',
          path:'/admin/liveevents'
        },
        {
          name: 'Archived Events',
          path:'/admin/archevents'
        }
      ]
    },
    {
      name: "Master data",
      path: '',
      child:[
        {
          name: 'Download Template',
          path:''
        },
        {
          name: 'Upload/Update Sheet',
          path:'/admin/updsheet',
        },
        {
          name: 'View Sheet',
          path:'/admin/viewsheet'
        }
      ]
    }
  ]

  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  actinactAllTabs(inp) {
    console.log(inp, 'inp');
    this.tabs.forEach(element => {
      element['active'] = inp
      if(element['child'] && element['child'].length){
        element['child'].forEach(function(ech){
          ech['active'] = inp
        })
      }
    });
  }

  downloadURI(uri) {
    var link = document.createElement("a");
    link.href = uri;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  }

  dowSheetFormat(){
    this.downloadURI('assets/csv/MasterData.csv')
  }

}
