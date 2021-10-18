import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { VehicleService } from '../vehicle.service';
import { Vehicle } from '../vehicle';
import { NavigateService } from '../navigate.service';

@Component({
  selector: 'app-vehicle-viewer',
  templateUrl: './vehicle-viewer.component.html',
  styleUrls: ['./vehicle-viewer.component.css']
})



export class VehicleViewerComponent implements OnInit {

  vehicle?: Vehicle;
  deleteClicked: boolean = false;

  constructor(private vehicleService: VehicleService, 
              private router : Router, 
              private activatedRoute : ActivatedRoute,
              private nav: NavigateService) { }

  ngOnInit(): void {
    const vehicleId = this.nav.getId(this.activatedRoute);
    if(vehicleId>0){  
      this.vehicleService.getVehicle(vehicleId).subscribe(
        (data: Vehicle)=>{
          this.vehicle = data;
        },(err: any)=>{
          console.log(err);
          this.nav.navigateToPageNotFound();
        })
    }else{
      this.nav.navigateToPageNotFound();
    }
    
  }

  tripCost(){
    this.router.navigate(['cost/'+this.vehicle?.id], {relativeTo: this.activatedRoute});
  }
  
  delete(){
    this.router.navigate(['delete/'+this.vehicle?.id], {relativeTo: this.activatedRoute});
  }
  
  updateVehicle(){
    this.router.navigate(['edit/'+this.vehicle?.id], {relativeTo: this.activatedRoute});
  }

}
