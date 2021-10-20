import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

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
                      private router: Router ) 
                      { }

  ngOnInit(): void {
    this.vehicleService.getAllVehicles().subscribe(
      (data: any) => {this.vehicles = data},
      (err: any) => {console.log(err)});
      
  }

  viewVehicle( id: number){
    this.router.navigate(['vehicles/' + id]);
  }

}
