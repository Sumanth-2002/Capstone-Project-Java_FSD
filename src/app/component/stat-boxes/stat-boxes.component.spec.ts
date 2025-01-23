import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatBoxesComponent } from './stat-boxes.component';

describe('StatBoxesComponent', () => {
  let component: StatBoxesComponent;
  let fixture: ComponentFixture<StatBoxesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StatBoxesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(StatBoxesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
