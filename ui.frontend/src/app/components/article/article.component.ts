import { Component, Input, OnInit  } from '@angular/core';
import {MapTo} from '@adobe/cq-angular-editable-components';

const ArticleEditConfig = {
  emptyLabel: 'Article',
  isEmpty: cqModel =>
    !cqModel || !cqModel.articleTitle || cqModel.articleTitle.trim().length < 1
};
@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {

  @Input() articleTitle: string;
  @Input() text: string;
  @Input() richText: true;

  constructor() { }

  ngOnInit(): void {
  }

}
MapTo('bookstore-spa/components/content/article')(ArticleComponent, ArticleEditConfig);

