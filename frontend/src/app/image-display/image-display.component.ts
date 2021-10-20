import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Image } from '../image';
import { ImageServiceService } from '../image-service.service';
import { NavigateService } from '../navigate.service';

@Component({
  selector: 'app-image-display',
  templateUrl: './image-display.component.html',
  styleUrls: ['./image-display.component.css']
})
export class ImageDisplayComponent implements OnInit {

  vehicleImg?: any;
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
          (image: Image) =>{        
            this.displayImage(image);
          },
          ()=>{
            this.nav.navigateToPageNotFound();
          }
       )
    }
  }

  displayImage(image: any){
    if(image){
      const objectURL = 'data:image/jpeg;base64,' + image.img;
      this.vehicleImg = this.sanitizer.bypassSecurityTrustUrl(objectURL);
      this.hasImage = true;
    }else{
      this.hasImage= false;
    } 
  }

}
