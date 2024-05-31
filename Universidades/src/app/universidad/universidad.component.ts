import { Component, OnInit } from '@angular/core';

import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-universidad',
  templateUrl: './universidad.component.html',
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent implements OnInit{
  universidades : Universidad[] =[];

  constructor(private servicioDeUniversidad: UniversidadService) {

  }

  ngOnInit(): void {
    this.getUniversidades();
  }

  getUniversidades(): void {
    this.servicioDeUniversidad.getUniversidades().subscribe(
      universidadesRecibidos => this.universidades = universidadesRecibidos);
   }
    /*
.subscribe(
  next: {heroesRecibidos => this.heroes = heroesRecibidos}
  error: {error => console.log(error)}
  complete: {}
)
    */
add(nombre: string): void {
  nombre = nombre.trim();
  if (!nombre) { return; }
  this.servicioDeUniversidad.addUniversidad({ nombre } as Universidad)
    .subscribe(universidad => {
      this.universidades.push(universidad);
    });
}
  delete(universidad: Universidad): void {
    this.universidades = this.universidades.filter(h => h !== universidad);
    this.servicioDeUniversidad.deleteUniversidad(universidad.id).subscribe();
  }
}
