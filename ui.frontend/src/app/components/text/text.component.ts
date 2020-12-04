import { MapTo } from '@adobe/aem-angular-editable-components';
import { Component, Input, HostBinding } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

/**
 * Default Edit configuration for the Text component that interact with the Core Text component and sub-types
 */
const TextEditConfig = {
  emptyLabel: 'Text',
  isEmpty: cqModel =>
    !cqModel || !cqModel.text || cqModel.text.trim().length < 1
};

@Component({
  selector: 'app-text',
  styleUrls: ['./text.component.css'],
  templateUrl: './text.component.html'
})
export class TextComponent {
  @Input() richText: boolean;
  @Input() text: string;
  @Input() itemName: string;

  @HostBinding('class') class = 'text__content';

  @HostBinding('innerHtml') get content() {
    return this.richText
      ? this.sanitizer.bypassSecurityTrustHtml(this.text)
      : this.text;
  }
  @HostBinding('attr.data-rte-editelement') editAttribute = true;

  constructor(private sanitizer: DomSanitizer) {}
}

MapTo('bookstore-spa/components/content/text')(
  TextComponent,
  TextEditConfig
);
