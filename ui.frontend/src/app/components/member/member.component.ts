import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, Input, OnInit } from '@angular/core';
import { AuthenticationService } from '../../service/authentication.service';
import { User } from '../../model/user';

const MemberEditConfig = {
  emptyLabel: 'Member',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1
};
@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']
})
export class MemberComponent implements OnInit {

  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  get memberName(): string {
    let name = 'unknown';
    if (this.authService.userValue) {
      name = this.authService.userValue.firstName;
    }
    return name;
  }
}

MapTo('bookstore-spa/components/content/member')(
  MemberComponent,
  MemberEditConfig
);
