import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { EventModel } from 'src/app/admin-data-models/event';
import { environment } from 'src/environments/environment';
import { BaseHttpClientService } from '../../common/base-http-client.service';

@Injectable({
  providedIn: 'root'
})
export class AdminArchEventsService {

  private baseUrl = environment.baseApplicationUrl+"/event";
 
  constructor(private _http:BaseHttpClientService) { }

  getArchievedEvents(): Observable<EventModel[]>{
    return this._http.getData<EventModel[]>(`${this.baseUrl}`+'/getArchievedEvents');
  }
}
