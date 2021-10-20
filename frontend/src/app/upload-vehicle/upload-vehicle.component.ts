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
              private nav: NavigateService) {
               }

  ngOnInit(): void {
  }
  
  validateInput(form: any){
    if(form.model && form.make && form.year && form.consumption  && 
    parseInt(form.year) === form.year && form.year > 0 && form.consumption > 0){
      this.validInput = true;
      this.postVehicle(form);
      
    }else{
      this.validInput = false;
    }
  }


  postVehicle(form: any){
    this.vehicleService.postVehicle(form.make, form.model, form.year, form.consumption).subscribe(
      (res: any)=>{
        if(isNaN(res)){
          this.nav.navigateToDashboard();
        }else{
          this.nav.navigateToVehicle(res);
        }
       
      },
      (err:any)=>{
        console.log(err);
      }
    )
  }

}
