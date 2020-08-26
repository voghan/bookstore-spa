import { Component, Input, OnInit } from '@angular/core';
import {MapTo} from '@adobe/cq-angular-editable-components';

const ButtonEditConfig = {
  emptyLabel: 'Button',
  isEmpty: cqModel =>
    !cqModel || !cqModel.link || cqModel.link.trim().length < 1
};

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.scss']
})
export class ButtonComponent implements OnInit {

  @Input() text: string;
  @Input() link: string;
  @Input() icon: string;
  @Input() buttonStyle: string;

  constructor() { }

  get hasContent(): boolean {
    return this.link && this.link.trim().length > 0;
  }

  ngOnInit(): void {
  }

}
MapTo('bookstore-spa/components/content/button')(ButtonComponent, ButtonEditConfig);
