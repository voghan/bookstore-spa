import { Component, Input, OnInit  } from '@angular/core';
import {MapTo} from '@adobe/cq-angular-editable-components';

const LinkEditConfig = {
  emptyLabel: 'Link',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1
};

@Component({
  selector: 'app-link',
  templateUrl: './link.component.html',
  styleUrls: ['./link.component.scss']
})
export class LinkComponent implements OnInit {

  @Input() text: string;
  @Input() link: string;
  @Input() route = true;

  constructor() { }

  ngOnInit(): void {
  }

}

MapTo('bookstore-spa/components/content/link')(LinkComponent, LinkEditConfig);

