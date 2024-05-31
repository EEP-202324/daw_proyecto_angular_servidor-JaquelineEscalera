import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppComponent } from './app.component';
import { UniversidadComponent } from './universidad/universidad.component';
import { UniversidadDetailComponent } from './universidad-detail/universidad-detail.component';
import { MensajesComponent } from './mensaje/mensaje.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './in-memory-data.service';
import { UniversidadSearchComponent } from './universidad-search/universidad-search.component';

@NgModule({
  declarations: [
    AppComponent,
    UniversidadComponent,
    UniversidadDetailComponent,
    MensajesComponent,
    DashboardComponent,
    UniversidadSearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { dataEncapsulation: false }
    )
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
