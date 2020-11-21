import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Group } from 'src/app/admin-data-models/group';
import { UserDetails } from 'src/app/admin-data-models/userDetails';
import { environment } from 'src/environments/environment';
import { BaseHttpClientService } from '../../common/base-http-client.service';

@Injectable({
  providedIn: 'root'
})
export class AdminGroupsService {

 
  constructor(private httpClient : HttpClient, private _http:BaseHttpClientService) { }

  private baseUrl = environment.baseApplicationUrl;
  
  getAllGroups(): Observable<any>{
    return this._http.getData<any>(`${this.baseUrl}`+'/groups/group');
  }

  postByGroup(group:any):Observable<Group>{
    return this._http.postData<Group>(`${this.baseUrl}`+'/groups/postgroup',group);
  }

  getUserDetailsByGroup(groupId:any): Observable<UserDetails[]> {
    return this._http.getData<UserDetails[]>(`${this.baseUrl}`+'/groups/groupById/'+groupId);
  }

  deleteByGroup(id:any): Observable<Group[]> {
    return this._http.delData<Group[]>(`${this.baseUrl}`+'/groups/removeGroup/'+id);
  }

  getAllusers(): Observable<any>{
    return this._http.getData<any>(`${this.baseUrl}`+'/user/getAllUsers');
  }

  updateGrpusers(inp_data): Observable<any>{
    return this._http.postFormData<any>(`${this.baseUrl}`+'/user/updateGroupOfUsersByEmpId', inp_data);
  }

  removeGrpusers(inp_data): Observable<any>{
    return this._http.postFormData<any>(`${this.baseUrl}`+'/user/removeGroupOfUsersByEmpId', inp_data);
  }

  getGrpusers(id): Observable<any>{
    return this._http.getData<any>(`${this.baseUrl}`+'/groups/groupById/'+id);
  }
}
