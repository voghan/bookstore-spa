import { Component, Input, OnInit, AfterViewInit, ViewChild, TemplateRef } from '@angular/core';
import {MapTo} from '@adobe/cq-angular-editable-components';
import { DomSanitizer } from '@angular/platform-browser';

const EmbedEditConfig = {
  emptyLabel: 'Embed',
  isEmpty: cqModel =>
    !cqModel || !cqModel.type || cqModel.type.trim().length < 1
};
@Component({
  selector: 'app-embed',
  templateUrl: './embed.component.html',
  styleUrls: ['./embed.component.scss']
})
export class EmbedComponent implements OnInit, AfterViewInit {

  constructor(private sanitizer: DomSanitizer) {}

  @Input() type: string;
  @Input() url: string;
  @Input() html: string;
  @Input() result: EmbedResult;
  @Input() ember: string;

  @Input() embedTemplate: TemplateRef<any>;

  @ViewChild('embedHtml')
  private embedHtmlTpl: TemplateRef<any>;

  @ViewChild('embedUrl')
  private embedUrlTpl: TemplateRef<any>;

  @ViewChild('embedEmbeddable')
    private embedEmbeddableTpl: TemplateRef<any>;

  get content() {
    return this.sanitizer.bypassSecurityTrustHtml(this.ember);
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
      if ( this.type === 'HTML'  ) {
        this.embedTemplate = this.embedHtmlTpl;
        this.ember = this.html;
      } else if ( this.type === 'URL'  ) {
        this.embedTemplate = this.embedUrlTpl;
        this.ember = this.result.options.response.html;
      } else if ( this.type === 'EMBEDDABLE'  ) {
        this.embedTemplate = this.embedEmbeddableTpl;
      }

      console.log('embedTemplate-' + this.embedTemplate);
  }

}

export class EmbedResult {
    @Input() processor: string;
    @Input() options: EmbedOption;
}

export class EmbedOption {
    @Input() provider: string;
    @Input() response: EmbedResponse;
}

export class EmbedResponse {
    @Input() html: string;

}

MapTo('bookstore-spa/components/content/embed')(EmbedComponent, EmbedEditConfig);
