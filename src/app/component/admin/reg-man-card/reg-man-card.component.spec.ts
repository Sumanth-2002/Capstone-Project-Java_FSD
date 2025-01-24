import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegManCardComponent } from './reg-man-card.component';

describe('RegManCardComponent', () => {
  let component: RegManCardComponent;
  let fixture: ComponentFixture<RegManCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegManCardComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegManCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
