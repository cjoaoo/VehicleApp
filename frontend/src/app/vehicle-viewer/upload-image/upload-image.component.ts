import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ImageServiceService } from 'src/app/image-service.service';
import { NavigateService } from 'src/app/navigate.service';

@Component({
  selector: 'app-upload-image',
  templateUrl: './upload-image.component.html',
  styleUrls: ['./upload-image.component.css']
})

export class UploadImageComponent implements OnInit {

  chosenImage: string = "";

  constructor( private imageService: ImageServiceService,
              private activatedRoute: ActivatedRoute,
              private nav: NavigateService) { }

  ngOnInit(): void {
  }

  fileChangeEvent(fileInput: any) {

    if (fileInput.target.files && fileInput.target.files[0]) {
        const reader = new FileReader();
        reader.onload = (e: any) => {
            const image = new Image();
            image.src = e.target.result;
            image.onload = (rs: any) => {
                //this.chosenImage = e.target.result;
                this.chosenImage = image.src;
            },
            (err:any)=>{console.log(err)};
        };
        //reader.readAsDataURL(fileInput.target.files[0]);
    }
}

submit(){

  const vehicleId = this.nav.getId(this.activatedRoute);
  this.imageService.putPicture(vehicleId, this.chosenImage).subscribe(
      (res:any)=>{
        console.log(res);
      }
  );
}
}
