import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DoTxnComponent } from './do-txn.component';

describe('DoTxnComponent', () => {
  let component: DoTxnComponent;
  let fixture: ComponentFixture<DoTxnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DoTxnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DoTxnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
