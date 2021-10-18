import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { VehiclesComponent } from './vehicles/vehicles.component';
import { VehicleViewerComponent } from './vehicle-viewer/vehicle-viewer.component';
import { CostComponent } from './vehicle-viewer/cost/cost.component';
import { UploadVehicleComponent } from './upload-vehicle/upload-vehicle.component';
import { TopNavBarComponent } from './top-nav-bar/top-nav-bar.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { FormsModule } from '@angular/forms';
import { DeleteComponent } from './vehicle-viewer/delete/delete.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    VehiclesComponent,
    VehicleViewerComponent,
    CostComponent,
    UploadVehicleComponent,
    TopNavBarComponent,
    PageNotFoundComponent,
    DeleteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
