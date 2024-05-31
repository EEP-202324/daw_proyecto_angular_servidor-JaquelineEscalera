import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UniversidadSearchComponent } from './universidad-search.component';

describe('UniversidadSearchComponent', () => {
  let component: UniversidadSearchComponent;
  let fixture: ComponentFixture<UniversidadSearchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [UniversidadSearchComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UniversidadSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
