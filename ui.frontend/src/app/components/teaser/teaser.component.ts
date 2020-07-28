import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, Input, OnInit } from '@angular/core';

const TeaserEditConfig = {
  emptyLabel: 'Teaser',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1
};

@Component({
  selector: 'app-teaser',
  templateUrl: './teaser.component.html',
  styleUrls: ['./teaser.component.css']
})
export class TeaserComponent implements OnInit {
    @Input() src: string;
    @Input() alt: string;
    @Input() title: string;
    @Input() teaserTitle: string;
    @Input() ctaLinkURL: string;
    @Input() ctaText: string;

    constructor() { }

    get hasContent(): boolean {
      return this.src && this.src.trim().length > 0;
    }

    get hasCTA(): boolean {
      return this.ctaLinkURL && this.ctaLinkURL.trim().length > 0 && this.ctaText && this.ctaText.trim().length > 0;
    }

    ngOnInit(): void {
    }

}
MapTo('bookstore-spa/components/content/teaser')(
    TeaserComponent,
    TeaserEditConfig
);


