import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, OnInit, Input } from '@angular/core';

const HelloworldEditConfig = {
  emptyLabel: 'Hello World',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1
};

@Component({
  selector: 'app-helloworld',
  templateUrl: './helloworld.component.html',
  styleUrls: ['./helloworld.component.scss']
})
export class HelloworldComponent implements OnInit {

  @Input() message: string;

  constructor() { }

  ngOnInit(): void {
  }

}

MapTo('bookstore-spa/components/content/helloworld')(
  HelloworldComponent,
  HelloworldEditConfig
);

