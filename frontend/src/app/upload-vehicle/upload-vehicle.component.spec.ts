import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UploadVehicleComponent } from './upload-vehicle.component';

describe('UploadVehicleComponent', () => {
  let component: UploadVehicleComponent;
  let fixture: ComponentFixture<UploadVehicleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UploadVehicleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UploadVehicleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
