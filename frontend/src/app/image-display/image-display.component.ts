import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Photo } from '../photo';
import { ImageServiceService } from '../image-service.service';
import { NavigateService } from '../navigate.service';

@Component({
  selector: 'app-image-display',
  templateUrl: './image-display.component.html',
  styleUrls: ['./image-display.component.css']
})
export class ImageDisplayComponent implements OnInit {

  vehicleImg?: Photo;
  hasImage: boolean = false;

  constructor(private nav: NavigateService,
              private activatedRoute: ActivatedRoute,
              private imgService: ImageServiceService,
              private sanitizer: DomSanitizer) { }

ngOnInit(): void {
  
  const vehicleId = this.nav.getId(this.activatedRoute);
  if(vehicleId > 0){
    this.imgService.getPicture(vehicleId)
      .subscribe(
        (image: Photo) =>{  
          this.vehicleImg = image;   
          this.vehicleImg.src = this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + this.vehicleImg.src);
          this.hasImage = true;
        },
        ()=>{
          console.log("This vehicle has no image.");
          this.hasImage= false;
        }
     )
  }
}

}