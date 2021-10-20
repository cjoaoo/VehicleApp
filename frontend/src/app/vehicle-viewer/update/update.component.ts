import { Component, OnInit, OnChanges } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NavigateService } from 'src/app/navigate.service';
import { VehicleService } from 'src/app/vehicle.service';

@Component({
  selector: 'app-update',
  templateUrl: './update.component.html',
  styleUrls: ['./update.component.css']
})
export class UpdateComponent implements OnInit, OnChanges {

  validInput: boolean = true;
  id?: number;
  instructions: string = "You may change one or more fields:";

  constructor(private vehicleService: VehicleService,
              private nav: NavigateService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.id = this.nav.getId(this.activatedRoute);
  }

  ngOnChanges(){

  }

  validateInput(form: any){

    const modelMakeFields = form.model || form.make;
    const yearField = !(isNaN(parseInt(form.year.toString())));
    const consumptionField = !(isNaN(parseInt(form.consumption.toString())));

    const updateAtLeastOne = modelMakeFields || yearField || consumptionField;

    const validYear = (yearField && form.year> 0) || !yearField;

    const validCons = (consumptionField && form.consumption> 0 )|| !consumptionField; 

    if(this.id && updateAtLeastOne && validYear && validCons){
      this.validInput = true;
      let year = yearField ? form.year : -1;
      let cons = consumptionField ? form.consumption : -1;

      this.updateVehicle(this.id, form.make, form.model, year, cons);
    }else{
      this.validInput = false;
      this.instructions = "Please insert valid values for the fields you wish to change.";
    }
  }


  updateVehicle(id: number, make: string, model: string, year: number, cons: number){

    this.vehicleService.updateVehicle(id, make, model, year, cons).subscribe(
      (res: Number)=>{
        this.nav.refresh();
      },
      (err:any)=>{
        console.log(err);
        this.nav.navigateToPageNotFound();
      }
    )
  }

}


