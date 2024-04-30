import { Component, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HousingService } from '../housing.service';
import { HousingLocation } from '../housinglocation';
import { CommonModule } from '@angular/common';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Peticion } from '../peticion';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './details.component.html',
  styleUrl: './details.component.css'
})
export class DetailsComponent {
  route: ActivatedRoute = inject(ActivatedRoute);
  servicioInmobiliario = inject(HousingService);
  casa: HousingLocation | undefined;

  formularioPeticion = new FormGroup({
    nombre: new FormControl(''),
    apellido: new FormControl(''),
    email: new FormControl('')
  });

  constructor() {
    const casaId = Number(this.route.snapshot.params['id']);
    this.servicioInmobiliario.getCasita(casaId).subscribe(
      casa => {
        this.casa = casa;
      }
    );
  }

  enviarPeticion() {
    this.servicioInmobiliario.submitApplication(
      this.formularioPeticion.value.nombre ?? '',
      this.formularioPeticion.value.apellido ?? '',
      this.formularioPeticion.value.email ?? ''
    );
    const peticion: Peticion = {...(this.formularioPeticion.value)};
    this.servicioInmobiliario.enviarPeticion(peticion).subscribe({
      next: peticion => {alert (peticion.nombre)},
      error: error => {alert("ERROR: " + error)}
  });
  }
}
