import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { UniversidadesComponent } from './universidades/universidades.component';
import { UniversidadDetailComponent } from './universidad-detail/universidad-detail.component';
import { MensajesComponent } from './mensajes/mensajes.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { AppRoutingModule } from './app-routing.module';

@NgModule({
  declarations: [
    AppComponent,
    UniversidadesComponent,
    UniversidadDetailComponent,
    MensajesComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
