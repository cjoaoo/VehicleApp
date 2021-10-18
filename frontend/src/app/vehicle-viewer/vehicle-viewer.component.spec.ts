import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VehicleViewerComponent } from './vehicle-viewer.component';

describe('VehicleViewerComponent', () => {
  let component: VehicleViewerComponent;
  let fixture: ComponentFixture<VehicleViewerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VehicleViewerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VehicleViewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
