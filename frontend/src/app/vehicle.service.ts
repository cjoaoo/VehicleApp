import { Injectable, NgModule } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Vehicle } from './vehicle';

@Injectable({
  providedIn: 'root'
})

export class VehicleService {

  endpoint='http://localhost:8080/api/vehicles/';

  httpOptions = {
    headers: new HttpHeaders({"Access-Control-Allow-Origin": "http://localhost:8080/"})

  };
  


  constructor(private http: HttpClient) { }

  getAllVehicles(){
    return this.http.get<Vehicle[]>(this.endpoint, this.httpOptions);
  }

  getVehicle(id: number){
    return this.http.get<Vehicle>(this.endpoint + id, this.httpOptions);
  }

  
  postVehicle(make: string, model: string, year: number, consumption: number){
    let params = new HttpParams()
    .set('model', model)
    .set('make', make)
    .set('year', year)
    .set('consumption', consumption);
    return this.http.post<Number>(this.endpoint, this.httpOptions, {params: params});
  }

  updateVehicle(id: number, make: string, model: string, year: number, consumption: number){
    let params = new HttpParams()
    .set('id', id)
    .set('model', model)
    .set('make', make)
    .set('year', year)
    .set('consumption', consumption);
    return this.http.put<Number>(this.endpoint+ id, this.httpOptions, {params: params});
  }
  

  deleteVehicle(id: number){
    return this.http.delete<Vehicle>(this.endpoint + id);
  }

  getTripCost(id:number, fuelCost:number, distance:number){
    let params = new HttpParams()
    .set('fuelCost', fuelCost)
    .set('distance', distance);
    return this.http.get<number>(this.endpoint + id + "/cost", {params: params});
  }

}
