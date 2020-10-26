import { Component, Input, OnInit  } from '@angular/core';
import {MapTo} from '@adobe/cq-angular-editable-components';

const AccordionEditConfig = {
  emptyLabel: 'Accordion',
  isEmpty: cqModel =>
    !cqModel || !cqModel.link || cqModel.link.trim().length < 1
};

@Component({
  selector: 'app-accordion',
  templateUrl: './accordion.component.html',
  styleUrls: ['./accordion.component.scss']
})
export class AccordionComponent implements OnInit {

  @Input() title: string;
  @Input() subtitle: string;

  constructor() { }

  ngOnInit(): void {
  }

}
MapTo('bookstore-spa/components/content/accordion')(AccordionComponent, AccordionEditConfig);
