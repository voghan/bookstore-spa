import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, OnInit } from '@angular/core';

const ListEditConfig = {
  emptyLabel: 'List',
  isEmpty: cqModel =>
    !cqModel
};

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.scss']
})
export class ListComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

MapTo('bookstore-spa/components/content/list')(
  ListComponent,
  ListEditConfig
);

