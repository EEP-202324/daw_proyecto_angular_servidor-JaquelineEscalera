import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms'; // <-- NgModel lives here
import { AppComponent } from './app.component';
import { UniversidadComponent } from './universidad/universidad.component';
import { UniversidadDetailComponent } from './universidad-detail/universidad-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    UniversidadComponent,
    UniversidadDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
