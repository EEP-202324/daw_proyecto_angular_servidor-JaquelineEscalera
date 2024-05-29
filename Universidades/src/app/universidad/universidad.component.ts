import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-universidad',
  templateUrl: './universidad.component.html',
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {
  universidad : Universidad[] =[];
  universidadSeleccionado?: Universidad;

  constructor(private servicioDeUniversidad: UniversidadService) {

  }

  ngOnInit(): void {
    this.getUniversidad();
  }

  getUniversidad(): void {
    this.servicioDeUniversidad.getUniversidad().subscribe(
      universidadesRecibidos => this.universidad = universidadesRecibidos
    );
  }

  onSelect(universidad: Universidad): void {
    this.universidadSeleccionado = universidad;
  }
}
