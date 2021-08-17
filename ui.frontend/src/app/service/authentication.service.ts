import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { catchError } from 'rxjs/internal/operators';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { BehaviorSubject, Observable, throwError } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '../../environments/environment';
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

  public isAuthenticated(): boolean {
      return !!this.userSubject.value;
    }

  login(username, password): Observable<any> {

    console.log('...auth service login');
    const token = 'eyJleHAiOjE2MDU5OTM1MTEsImlhdCI6MTYwNTk5MjkxMX0.7d8oGLEcfeLpHe9yO6KwZSX6PqgFtdrMa7Gp2UTbgek';
    return this.http.post<any>(environment.apiUrl + '/bin/authenticate',  { username, password, token }).pipe(
      map(response => this.extractData(response)),
      catchError(error => this.handleError(error))
    );

  }

  private extractData(user: User): any {
    console.log(user);
    this.userSubject.next(user);
    // store user details and jwt token in local storage to keep user logged in between page refreshes
    localStorage.setItem('user', JSON.stringify(user));
    this.router.navigate(['/content/bookstore-spa/us/en/home/my-account']);
  }

  logout() {
    localStorage.removeItem('user');
    this.userSubject.next(null);
  }

  private handleError(error: HttpErrorResponse): any {
    console.log('..... handleError-');
    console.error(error);
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
