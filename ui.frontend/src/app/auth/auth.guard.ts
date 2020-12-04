import { Injectable } from '@angular/core';
import { Location } from '@angular/common';
import { Utils } from '@adobe/aem-angular-editable-components';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(public auth: AuthenticationService, public router: Router, location: Location) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    console.log(route);



    if (state.url.includes('home/my-account') && !this.isInEditor()) {
      console.log(' is authenticated? ' + this.auth.isAuthenticated());
      if (!this.auth.isAuthenticated()) {
        this.router.navigate(['/content/bookstore-spa/us/en/home/login']);
        return false;
      }
    }

    return true;
  }

  /*
  * Need to make sure the author instance is not blocked by authentication
  */
  isInEditor(): boolean {
    console.log('...... on author ' + (location.port === '4502'));
    console.log('...... in editor ' + Utils.isInEditor());

    return location.port === '4502';
  }

}
