import { Component, Input, OnInit } from '@angular/core';
import {MapTo} from '@adobe/cq-angular-editable-components';

const CustomEditConfig = {
  emptyLabel: 'Custom Component',
  isEmpty: cqModel =>
    !cqModel || !cqModel.message || cqModel.message.trim().length < 1
};

@Component({
  selector: 'app-custom',
  templateUrl: './custom.component.html',
  styleUrls: ['./custom.component.scss']
})
export class CustomComponent implements OnInit {

  @Input() message: string;

  constructor() { }

  ngOnInit(): void {
  }

}

MapTo('bookstore-spa/components/content/customcomponent')(CustomComponent, CustomEditConfig);
