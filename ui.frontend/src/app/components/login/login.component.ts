import { MapTo } from '@adobe/aem-angular-editable-components';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../../service/authentication.service';
import { User } from '../../model/user';

const LoginEditConfig = {
  emptyLabel: 'Login',
  isEmpty: cqModel =>
    !cqModel
};

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  form: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  user: User;

  constructor(
      private formBuilder: FormBuilder,
      private authService: AuthenticationService
    ) {
      this.form = this.formBuilder.group({
          username: ['', Validators.required],
          password: ['', Validators.required]
        });
    }

  ngOnInit(): void {

  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit(): void {
      this.submitted = true;
      this.authService.login(this.f.username.value, this.f.password.value).subscribe(
        {
          next: () => {
            console.log('....returned user - ' + this.authService.userValue);
          }
        });
  }

}

MapTo('bookstore-spa/components/content/login')(
  LoginComponent,
  LoginEditConfig
);
