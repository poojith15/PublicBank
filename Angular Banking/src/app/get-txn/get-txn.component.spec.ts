import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GetTxnComponent } from './get-txn.component';

describe('GetTxnComponent', () => {
  let component: GetTxnComponent;
  let fixture: ComponentFixture<GetTxnComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GetTxnComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GetTxnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
