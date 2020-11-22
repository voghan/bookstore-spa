import { TestBed } from '@angular/core/testing';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { MemberGuard } from './member.guard';

describe('MemberGuard', () => {
  let guard: MemberGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AppRoutingModule, HttpClientTestingModule]
    });
    guard = TestBed.inject(MemberGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
