import { Injectable } from '@angular/core';
import { CanActivate, Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService implements CanActivate {
  constructor(public router: Router) {}

  canActivate(): boolean {
    let isTokenPresent = localStorage.getItem("canShow");
    console.log("isTokenPresent------>"+isTokenPresent);
    if (isTokenPresent != 'true') {
      this.router.navigate(['/logout']);
      return false;
    }
    return true;
  } 
}
