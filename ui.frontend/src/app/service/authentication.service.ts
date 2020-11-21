import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/internal/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from 'src/environments/environment';
import { User } from '../model/user';


@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  private userSubject: BehaviorSubject<User>;
  public user: Observable<User>;

  constructor(
    private router: Router,
    private http: HttpClient
  ) {
    this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
    this.user = this.userSubject.asObservable();
  }

  public get userValue(): User {
    return this.userSubject.value;
  }

  login(username, password) {

    console.log('...auth service login');
    console.log('..... username-' + username);
    console.log('..... password-' + password);

    return this.http.get<any>('http://localhost:4503/bin/authenticate').pipe(
      map(user => {
          console.log('..... user-' + user);
          this.userSubject.next(user);
          // store user details and jwt token in local storage to keep user logged in between page refreshes
          localStorage.setItem('user', JSON.stringify(user));

      }),
      catchError(this.handleError)
    );

//     console.log('......calling ' + ${environment.apiUrl} + '/bin/authenticate');
//     return this.http.post<any>('http://localhost:4503/bin/authenticate', { username, password })
//       .pipe(map(user => {
//         console.log('..... user-' + user);
//         // store user details and jwt token in local storage to keep user logged in between page refreshes
//         localStorage.setItem('user', JSON.stringify(user));
//
//     }));

  }

  logout() {
    localStorage.removeItem('user');
  }

  private extractData(res: Response): any {
    const body = res;
    return body || { };
  }

  private handleError(error: HttpErrorResponse): any {
    if (error.error instanceof ErrorEvent) {
      console.error('An error occurred:', error.error.message);
    } else {
      console.error(
        `Backend returned code ${error.status}, ` +
        `body was: ${error.error}`);
    }
    return throwError(
      'Something bad happened; please try again later.');
  }

}
