import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, Input, OnInit, ViewChild, TemplateRef } from '@angular/core';

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
  @Input() linkURL: string;
  @Input() linkDisabled: boolean;

  @Input() titleTemplate: TemplateRef<any>;

  @ViewChild('titleOnly')
  private titleOnlyTpl: TemplateRef<any>;

  @ViewChild('titleLink')
  private titleLinkTpl: TemplateRef<any>;

  constructor() { }

  ngOnInit(): void {
    this.titleTemplate = this.titleOnlyTpl;
    console.log('titleTemplate-' + this.titleTemplate);
    console.log('titleOnlyTpl-' + this.titleOnlyTpl);
    console.log('titleLinkTpl-' + this.titleLinkTpl);
  }

  get hasLink(): boolean {
    return this.linkURL && this.linkURL.trim().length > 0 && !this.linkDisabled;
  }

}

MapTo('bookstore-spa/components/content/title')(
  TitleComponent,
  TitleEditConfig
);
