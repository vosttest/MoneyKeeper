import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAccountAddComponent } from './manage-account-add.component';

describe('ManageAccountAddComponent', () => {
  let component: ManageAccountAddComponent;
  let fixture: ComponentFixture<ManageAccountAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageAccountAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageAccountAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
