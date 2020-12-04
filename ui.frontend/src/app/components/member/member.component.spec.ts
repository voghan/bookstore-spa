import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AppRoutingModule } from '../../app-routing.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MemberComponent } from './member.component';

describe('MemberComponent', () => {
  let component: MemberComponent;
  let fixture: ComponentFixture<MemberComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MemberComponent ],
      imports: [ AppRoutingModule, HttpClientTestingModule ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
