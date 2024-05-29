import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';
import { MensajeService } from '../mensaje.service';

@Component({
  selector: 'app-universidad',
  templateUrl: './universidad.component.html',
  styleUrl: './universidad.component.css'
})
export class UniversidadComponent {
  universidad : Universidad[] =[];
  universidadSeleccionado?: Universidad;

  constructor(private servicioDeUniversidad: UniversidadService, private servicioDeMensajes: MensajeService) {

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
    this.servicioDeMensajes.add(`UniversidadComponent: Universidad seleccionado: id=${universidad.id}`)
  }
}
