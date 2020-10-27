import {AfterViewInit, ChangeDetectorRef, Component, HostBinding, Input, OnInit} from '@angular/core';
import {AEMAllowedComponentsContainerComponent, MapTo} from '@adobe/cq-angular-editable-components';

const CONTAINER_CLASS_NAMES = 'aem-tabs';

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
export class AccordionComponent extends AEMAllowedComponentsContainerComponent implements OnInit, AfterViewInit {

  @Input() title: string;
  @Input() subtitle: string;

  constructor(private changeDetectorRef: ChangeDetectorRef) {
      super();
  }

  getItemStyle(itemKey: string) {
      const display = 'block';
      return { display };
  }

  ngOnInit(): void {

  }

  ngAfterViewInit(): void {

  }

  /**
   * Returns the class names of the container based on the data from the cqModel
   */
  getHostClassNames() {
      return CONTAINER_CLASS_NAMES;
  }

  get hostClasses() {
      return this.getHostClassNames();
  }

}
MapTo('bookstore-spa/components/content/accordion')(AccordionComponent, AccordionEditConfig);
