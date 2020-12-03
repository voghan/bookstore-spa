import { Injectable } from '@angular/core';
import { Utils } from '@adobe/aem-angular-editable-components';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../service/authentication.service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(public auth: AuthenticationService, public router: Router) {}

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

      if (!Utils.isInEditor) {
      console.log('......not in editor');
      } else {
        console.log('...... in editor');
      }

      if (state.url.includes('home/my-account')) {
        console.log(' is authenticated? ' + this.auth.isAuthenticated());
        if (!this.auth.isAuthenticated()) {
          this.router.navigate(['/content/bookstore-spa/us/en/home/login']);
          return false;
        }
      }

      return true;
    }

}
