import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Photo } from './photo';

@Injectable({
  providedIn: 'root'
})
export class ImageServiceService {

  endpoint='http://localhost:8080/api/vehicles/';
  httpOptions = {
    headers: new HttpHeaders({"Access-Control-Allow-Origin": "http://localhost:8080/"})
  };

  constructor(private http: HttpClient) { }

  getPicture(vehicleId: number){
    return this.http.get<Photo>(this.endpoint + vehicleId + "/img", this.httpOptions);
  }

  putPicture(vehicleId: number, img: any){
    
    let params = new HttpParams()
    .set('imageFile', img)
    .set('imageName', 'nome');
    console.log(params);
    return this.http.put(this.endpoint + vehicleId + "/img", this.httpOptions, {params: params});
    
  }
}
