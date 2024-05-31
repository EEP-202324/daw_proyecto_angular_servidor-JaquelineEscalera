import { Component, OnInit } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';
import { MessageService } from '../message.service';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent implements OnInit {
  constructor(private universidadService: UniversidadService, private messageService: MessageService) {}
  universidades: Universidad[] = [];

  ngOnInit(): void {
    this.getUniversidades();
  }
  getUniversidades(): void {
    this.universidadService.getUniversidades()
        .subscribe(universidades => this.universidades = universidades);
  }

  add(nombre: string): void {
    nombre = nombre.trim();
    if (!nombre) { return; }
    this.universidadService.addUnversidad({ nombre } as Universidad)
      .subscribe(universidad => {
        this.universidades.push(universidad);
      });
  }

  delete(universidad: Universidad): void {
    this.universidades = this.universidades.filter(h => h !== universidad);
    this.universidadService.deleteUniversidad(universidad.id).subscribe();
  }
}
