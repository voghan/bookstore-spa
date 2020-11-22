import { TestBed } from '@angular/core/testing';
import { AppRoutingModule } from '../app-routing.module';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { AuthorGuard } from './author.guard';

describe('AuthorGuard', () => {
  let guard: AuthorGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [AppRoutingModule, HttpClientTestingModule]
    });
    guard = TestBed.inject(AuthorGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
