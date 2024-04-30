import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { HousingLocationComponent } from '../housing-location/housing-location.component';
import { HousingLocation } from '../housinglocation';
import { HousingService } from '../housing.service';
import { of } from 'rxjs';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    HousingLocationComponent
  ],
  template: `
  <section>
    <form>
      <input type="text" placeholder="Filter by city" #filter>
      <button class="primary" type="button" (click)="filtrar(filter.value)" >Search</button>
    </form>
  </section>
  <!--
  <section>
    <ul><li *ngFor="let casa of listaCasas">{{casa.name}}</li></ul>
  </section>
  -->
  <section class="results">
    <app-housing-location
      *ngFor="let casa of listaCasasFiltrada"
      [housingLocation]="casa"
    >
    </app-housing-location>
  </section>
  `,
  styleUrl: './home.component.css'
})

export class HomeComponent {
  listaCasas: HousingLocation[] = [];
  servicioInmobiliario: HousingService = inject(HousingService);

  listaCasasFiltrada : HousingLocation[] = [];

  constructor() {
    /*this.servicioInmobiliario.getAllHousingLocations().then(
      (casasPrometidas : HousingLocation[]) => {
        this.listaCasas = casasPrometidas;
        this.listaCasasFiltrada = casasPrometidas;
      }
    );*/

   this.servicioInmobiliario.getCasitas().subscribe(
    casas => {
      this.listaCasas = casas;
      this.listaCasasFiltrada = casas;
    }
   );
  }

  filtrar(text: string) {
    if (!text) {
      this.listaCasasFiltrada = this.listaCasas;
    } else {
      this.listaCasasFiltrada = this.listaCasas.filter(
        casa => casa?.city.toLowerCase().includes(text.toLowerCase())
      );
    }
  }
}
