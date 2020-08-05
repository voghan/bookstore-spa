import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, Input, OnInit, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';

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
export class TitleComponent implements OnInit, AfterViewInit {

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
  }

  ngAfterViewInit(): void {
    if ( this.hasLink ) {
      this.titleTemplate = this.titleLinkTpl;
    } else {
      this.titleTemplate = this.titleOnlyTpl;
    }

    console.log('titleTemplate-' + this.titleTemplate);
  }

  get hasLink(): boolean {
    return this.linkURL && this.linkURL.trim().length > 0 && !this.linkDisabled;
  }

}

MapTo('bookstore-spa/components/content/title')(
  TitleComponent,
  TitleEditConfig
);
