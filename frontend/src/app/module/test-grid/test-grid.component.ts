import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test-grid',
  templateUrl: './test-grid.component.html',
  styleUrls: ['./test-grid.component.css']
})
export class TestGridComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  columnDefs = [
    { field: 'make', sortable: true, filter: true, editable: true, },
    { field: 'model', sortable: true, filter: true , editable: true,},
    { field: 'price', sortable: true, filter: true , editable: true,},
    { field: 'design', sortable: true, filter: true , editable: true,},
  ];

  rowData = [
    { make: 'Toyota', model: 'Celica', design: 'classic', price: 35000 },
    { make: 'Ford', model: 'Mondeo', design: 'classic', price: 32000 },
    { make: 'Porsche', model: 'Sofgr', design: 'classic', price: 62000 },
    { make: 'Benz', model: 'SDG', design: 'classic', price: 72000 },
    { make: 'Tokyo', model: 'LIGTR', design: 'classic', price: 82000 },
    { make: 'TYa', model: 'IUOPY', design: 'classic', price: 92000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 },
    { make: 'Zoom', model: 'RTYUI', design: 'classic', price: 42000 }
  ];

}
