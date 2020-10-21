import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Grouplist } from '../module/groups/grouplist';

@Injectable({
  providedIn: 'root'
})
export class GroupsService {

  private listGroup = "http://localhost:8080/groups/group"; // URL to web api
  httpOptions = {
    headers: new HttpHeaders(),
    withCredentials: true
  };

  constructor(private http: HttpClient) { }

  getGroupByDesignation(): Observable<Grouplist> {
    return this.http.get<Grouplist>(`http://localhost:8080/groups/group`);
  }


  private handleError<T>(operation = "operation", result?: T) {
    return (error: any): Observable<T> => {
      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}