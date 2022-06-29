import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateCategoryComponent } from './create-agreement.component';

describe('CreateAgreementComponent', () => {
  let component: CreateAgreementComponent;
  let fixture: ComponentFixture<CreateAgreementComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateAgreementComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CreateAgreementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
