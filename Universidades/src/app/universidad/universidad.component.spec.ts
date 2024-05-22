import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversidadComponent } from './universidad.component';

describe('UniversidadComponent', () => {
  let component: UniversidadComponent;
  let fixture: ComponentFixture<UniversidadComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UniversidadComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UniversidadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
