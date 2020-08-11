import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, OnInit } from '@angular/core';

const SeparatorEditConfig = {
  emptyLabel: 'Separator',
  isEmpty: cqModel =>
    !cqModel
};

@Component({
  selector: 'app-separator',
  styleUrls: ['./separator.component.scss'],
  templateUrl: './separator.component.html'
})
export class SeparatorComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}

MapTo('bookstore-spa/components/content/separator')(
  SeparatorComponent,
  SeparatorEditConfig
);
