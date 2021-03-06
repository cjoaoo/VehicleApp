import { Injectable } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class NavigateService {

  constructor(private router : Router,
              private activatedRoute: ActivatedRoute) { }

  navigateToDashboard(){
    this.router.navigate(["/vehicles"]);
  }

  navigateToHome(){
    this.router.navigate(["/home"]);
  }

  navigateToPageNotFound(){
    this.router.navigate(["/page-not-found"]);
  }

  navigateToVehicle(id: number){
    this.router.navigate(["/vehicles/" + id]);
  }

  refresh(){
    window.location.reload();
  }

  isValidId(id: any) : boolean{
   return id && !isNaN(parseInt(id));
  }
  

  getId(actRoute: ActivatedRoute): number{
    const id = actRoute.snapshot.paramMap.get('id');
    if (id && !isNaN(parseInt(id))){
      return parseInt(id);
    }else{
      return -1;
    }
  }

}
