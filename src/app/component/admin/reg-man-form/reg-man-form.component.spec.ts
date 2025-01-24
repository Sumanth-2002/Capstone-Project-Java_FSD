import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegManFormComponent } from './reg-man-form.component';

describe('RegManFormComponent', () => {
  let component: RegManFormComponent;
  let fixture: ComponentFixture<RegManFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegManFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegManFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
