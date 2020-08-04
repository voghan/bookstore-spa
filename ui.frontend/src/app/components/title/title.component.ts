import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, Input, OnInit } from '@angular/core';

const TitleEditConfig = {
  emptyLabel: 'Title',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1
};

@Component({
  selector: 'app-title',
  templateUrl: './title.component.html',
  styleUrls: ['./title.component.scss']
})
export class TitleComponent implements OnInit {

  @Input() text: string;

  constructor() { }

  ngOnInit(): void {
  }

}

MapTo('bookstore-spa/components/content/title')(
  TitleComponent,
  TitleEditConfig
);
