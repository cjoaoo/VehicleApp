import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NavigateService } from 'src/app/navigate.service';
import { VehicleService } from 'src/app/vehicle.service';

@Component({
  selector: 'app-delete',
  templateUrl: './delete.component.html',
  styleUrls: ['./delete.component.css']
})
export class DeleteComponent implements OnInit {

  deleteClicked: boolean = true;
  
  constructor(private vehicleService: VehicleService,
              private nav: NavigateService,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
  }

  clickDelete(){
    this.deleteClicked = !this.deleteClicked;
  }

  deleteVehicle(){
    const id = this.nav.getId(this.activatedRoute);
    this.vehicleService.deleteVehicle(id).subscribe(
      (res:any)=>{
        this.nav.navigateToDashboard();
      }
    );
    
  }

}
