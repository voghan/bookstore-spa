import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, OnInit, Input } from '@angular/core';
import { ListItem } from './list-item';

const ListEditConfig = {
  emptyLabel: 'List',
  isEmpty: cqModel =>
    !cqModel || !cqModel.items || cqModel.items.length < 1
};

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  @Input() items: ListItem[];

  constructor() { }

  ngOnInit(): void {
    console.log('.....List');
  }

  get hasContent(): boolean {
    return this.items && this.items.length > 0;
  }

}

MapTo('bookstore-spa/components/content/list')(
  ListComponent,
  ListEditConfig
);

