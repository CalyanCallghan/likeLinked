import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { BaseHttpClientService } from '../../common/base-http-client.service';

@Injectable({
  providedIn: 'root'
})
export class AdminViewSheetService {

  baseUpi = environment.baseApplicationUrl
  constructor(private _http:BaseHttpClientService) { }

  getSheetData(){
    return this._http.getData<any>(this.baseUpi + '/user/getUsers');
  }
  updSheetRow(inp_data){
    console.log(JSON.stringify(inp_data));
    return this._http.putData<any>(this.baseUpi + '/user/updateUser', inp_data);
  }
  delSheetRow(inp_data){
    return this._http.delData<any>(this.baseUpi + '/user/deleteUser/'+inp_data);
  }
}
