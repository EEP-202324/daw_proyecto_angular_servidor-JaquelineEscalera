import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UniversidadesComponent } from './universidades/universidades.component';
import { FormsModule } from '@angular/forms';
import { UniversidadDetailComponent } from './universidad-detail/universidad-detail.component';
import { MessagesComponent } from './messages/messages.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { UniversidadSearchComponent } from './universidad-search/universidad-search.component';
import { AgregarUniversidadComponent } from './agregar-universidad/agregar-universidad.component';

@NgModule({
  declarations: [
    AppComponent,
    UniversidadesComponent,
    UniversidadDetailComponent,
    MessagesComponent,
    DashboardComponent,
    UniversidadSearchComponent,
    AgregarUniversidadComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
