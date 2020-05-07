import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetNoTxnComponent } from './get-no-txn.component';

describe('GetNoTxnComponent', () => {
  let component: GetNoTxnComponent;
  let fixture: ComponentFixture<GetNoTxnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetNoTxnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetNoTxnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
