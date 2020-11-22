import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(public auth: AuthenticationService, public router: Router) {}

    canActivate(): boolean {
      if (!this.auth.isAuthenticated()) {
        this.router.navigate(['login']);
        return false;
      }
      return true;
    }

}
