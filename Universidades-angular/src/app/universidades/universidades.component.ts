import { Component } from '@angular/core';
import { Universidad } from '../universidad';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent {
  universidad: Universidad = {
    id: 1,
    nombre: 'Complutense'
  };
}
