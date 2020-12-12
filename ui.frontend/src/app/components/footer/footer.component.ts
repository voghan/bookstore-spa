import { Component, OnInit } from '@angular/core';
import { MapTo } from '@adobe/aem-angular-editable-components';

const FooterEditConfig = {
  emptyLabel: 'Footer',
  isEmpty: cqModel =>
    !cqModel
};

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.scss']
})
export class FooterComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}

MapTo('bookstore-spa/components/structure/footer')(FooterComponent, FooterEditConfig);
