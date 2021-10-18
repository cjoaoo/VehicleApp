import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';

import { Vehicle } from '../vehicle';
import { VehicleService } from '../vehicle.service';

@Component({
  selector: 'app-vehicles',
  templateUrl: './vehicles.component.html',
  styleUrls: ['./vehicles.component.css']
})
export class VehiclesComponent implements OnInit {

  vehicles : Vehicle[] = [];

  constructor(private vehicleService: VehicleService, 
                      private activatedRoute : ActivatedRoute,
                      private router: Router ) 
                      { }

  ngOnInit(): void {
    this.vehicleService.getAllVehicles().subscribe(
      (data => this.vehicles = data));
      
  }

  viewVehicle( id: number){
    this.router.navigate(['vehicles/' + id]);
  }

}
