import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RegionalManagerComponent } from './regional-manager.component';

describe('RegionalManagerComponent', () => {
  let component: RegionalManagerComponent;
  let fixture: ComponentFixture<RegionalManagerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RegionalManagerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(RegionalManagerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
