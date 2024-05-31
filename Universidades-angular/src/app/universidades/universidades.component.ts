import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';
import { MensajeService } from '../mensaje.service';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent {

  selectedUniversidad?: Universidad;
  universidades: Universidad[] = [];

  constructor(private universidadService: UniversidadService, private mensajeService: MensajeService) {}

ngOnInit(): void {
    this.getUniversidades();
  }

onSelect(universidad: Universidad): void {
  this.selectedUniversidad = universidad;
  this.mensajeService.add(`UniversidadesComponent: Selected universidad id=${universidad.id}`);
  }

getUniversidades(): void {
  this.universidadService.getUniversidades()
      .subscribe(universidades => this.universidades = universidades);
  }
}
