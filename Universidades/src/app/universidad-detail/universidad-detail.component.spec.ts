import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversidadDetailComponent } from './universidad-detail.component';

describe('UniversidadDetailComponent', () => {
  let component: UniversidadDetailComponent;
  let fixture: ComponentFixture<UniversidadDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UniversidadDetailComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UniversidadDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
