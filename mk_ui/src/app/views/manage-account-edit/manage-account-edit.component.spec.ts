import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAccountEditComponent } from './manage-account-edit.component';

describe('ManageAccountEditComponent', () => {
  let component: ManageAccountEditComponent;
  let fixture: ComponentFixture<ManageAccountEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManageAccountEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManageAccountEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
