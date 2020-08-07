import { MapTo } from '@adobe/cq-angular-editable-components';
import { Component, Input, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

const TeaserEditConfig = {
  emptyLabel: 'Teaser',
  isEmpty: cqModel =>
    !cqModel || !cqModel.title || cqModel.title.trim().length < 1
};

@Component({
  selector: 'app-teaser',
  templateUrl: './teaser.component.html',
  styleUrls: ['./teaser.component.scss']
})
export class TeaserComponent implements OnInit {

    @Input() title: string;
    @Input() description: string;
    @Input() linkURL: string;
    @Input() actionsEnabled: boolean;
    @Input() imageLinkHidden: boolean;
    @Input() titleLinkHidden: boolean;
    @Input() imagePath: string;
    @Input() actions: TeaserAction[];

    constructor(private sanitizer: DomSanitizer) {}

    ngOnInit(): void {
    }

    get hasContent(): boolean {
      return this.title && this.title.trim().length > 0;
    }

    get hasImage(): boolean {
      return this.imagePath && this.imagePath.trim().length > 0;
    }

}

export class TeaserAction {

    @Input() title: string;
    @Input() url: string;
}

MapTo('bookstore-spa/components/content/teaser')(
    TeaserComponent,
    TeaserEditConfig
);


