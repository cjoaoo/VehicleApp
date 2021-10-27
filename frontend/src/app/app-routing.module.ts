import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { UploadVehicleComponent } from './upload-vehicle/upload-vehicle.component';
import { CostComponent } from './vehicle-viewer/cost/cost.component';
import { DeleteComponent } from './vehicle-viewer/delete/delete.component';
import { UpdateComponent } from './vehicle-viewer/update/update.component';
import { UploadImageComponent } from './vehicle-viewer/upload-image/upload-image.component';
import { VehicleViewerComponent } from './vehicle-viewer/vehicle-viewer.component';
import { VehiclesComponent } from './vehicles/vehicles.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: '', redirectTo: 'home', pathMatch:'full'},
  { path: 'vehicles', component: VehiclesComponent},
      {path : 'vehicles/:id' , component : VehicleViewerComponent,
          children: [
            { path: 'cost/:id', component: CostComponent},
            { path: 'delete/:id', component: DeleteComponent},
            { path: 'edit/:id', component: UpdateComponent} //,
          //  { path: 'imgupload/:id', component: UploadImageComponent}
          ]},
    { path: 'upload', component: UploadVehicleComponent},
  { path: '**', component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
