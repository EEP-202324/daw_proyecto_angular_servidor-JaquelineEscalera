import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import { UNIVERSIDADES } from '../mock-universidades';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent {
  universidades: Universidad[] = UNIVERSIDADES;
  selectedUniversidad?: Universidad;

onSelect(universidad: Universidad): void {
  this.selectedUniversidad = universidad;
}
}
