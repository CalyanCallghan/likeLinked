import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { BaseHttpClientService } from '../../common/base-http-client.service';

@Injectable({
  providedIn: 'root'
})
export class AdminUpdSheetService {

  baseUpi = environment.baseApplicationUrl
  constructor(private _http:BaseHttpClientService) { }

  uploadSheet(f_data){
    return this._http.postFormData<any>(this.baseUpi + '/doBatch/storeAndProcessFile' , f_data);
  }

  save_records(inp_data){
    return this._http.postData<any>(this.baseUpi + '/user/saveAll' , inp_data)
  }

}
