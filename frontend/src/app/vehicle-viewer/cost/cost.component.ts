import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NavigateService } from 'src/app/navigate.service';
import { VehicleService } from 'src/app/vehicle.service';

@Component({
  selector: 'app-cost',
  templateUrl: './cost.component.html',
  styleUrls: ['./cost.component.css']
})

export class CostComponent implements OnInit {

  fuel? : number;
  distance? : number;
  isValid : boolean = true;
  cost?: string;

  constructor(private vehicleService: VehicleService,
              private activatedRoute : ActivatedRoute,
              private nav: NavigateService) { }

  ngOnInit(): void {
  }

  validateInput(numbers: any){
    if(numbers.fuel && numbers.distance){
    if(isNaN(numbers.fuel) || isNaN(numbers.distance) || numbers.fuel <= 0 || numbers.distance <= 0 ){
      this.isValid = false;
    }else{
      this.isValid = true;
      this.getTripCost(numbers.fuel, numbers.distance);
    }
  }
  }

  getTripCost(fuel: number, distance: number){
    const vehicleId = this.activatedRoute.snapshot.paramMap.get('id');
    console.log(this.activatedRoute.snapshot);
    if(vehicleId && this.nav.isValidId(vehicleId)){
      this.vehicleService.getTripCost(parseInt(vehicleId), fuel, distance).subscribe(
        (data: number)=>{
          this.cost = data.toFixed(2);
        },(err: any)=>{
          console.log(err);
          this.nav.navigateToPageNotFound();
        })
    }else{
      console.log("aqui");
      this.nav.navigateToPageNotFound();
    }
  }
}
