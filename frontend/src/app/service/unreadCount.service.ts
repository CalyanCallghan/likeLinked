import { Injectable } from '@angular/core';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UnReadService {
  private canShow = new Subject<any>();
  constructor() { }
  setcanCount(canShow: any) {
    this.canShow.next(canShow);
  }

  getCount(): Observable<any> {
    return this.canShow.asObservable();
  }

  clearcanShows() {
    this.canShow.next(null);
  }
}
