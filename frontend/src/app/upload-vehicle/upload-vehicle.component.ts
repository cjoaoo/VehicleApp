import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NavigateService } from '../navigate.service';
import { VehicleService } from '../vehicle.service';

@Component({
  selector: 'app-upload-vehicle',
  templateUrl: './upload-vehicle.component.html',
  styleUrls: ['./upload-vehicle.component.css']
})
export class UploadVehicleComponent implements OnInit {

  validInput: boolean = true;

  constructor(private vehicleService: VehicleService,
              private nav: NavigateService,
              private activatedRoute: ActivatedRoute) {
               }

  ngOnInit(): void {
  }
  
  validateInput(form: any){
    if(form.model && form.make && form.year && form.consumption && !isNaN(form.year) && 
    parseInt(form.year) === Number(form.year) && Number(form.year) > 0 && !isNaN(form.consumption) && Number(form.consumption) > 0){
      this.validInput = true;
      const id = this.nav.getId(this.activatedRoute)
      if( id === -1){ // there is no id in the route = post request
        this.postVehicle(form);
      }else{
        this.updateVehicle(id, form);
      }


      
    }else{
      this.validInput = false;
      console.log(this.validInput);
    }
  }


  postVehicle(form: any){
    this.vehicleService.postVehicle(form.make, form.model, form.year, form.consumption).subscribe(
      (res: Number)=>{
       this.nav.navigateToDashboard();
      },
      (err:any)=>{
        console.log(err);
      }
    )
  }

  updateVehicle(id: number, form: any){
    this.vehicleService.updateVehicle(id, form.make, form.model, form.year, form.consumption).subscribe(
      (res: Number)=>{
        this.nav.navigateToDashboard();
      },
      (err:any)=>{
        console.log(err);
        this.nav.navigateToPageNotFound();
      }
    )
  }
  

}
