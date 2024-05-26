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

  constructor(private UniversidadService: UniversidadService) {

  }

  ngOnInit(): void {
    this.getUniversidad();
  }

  getUniversidad(): void {
    this.UniversidadService.getUniversidad().subscribe(
      Recibidos => this.universidad = this.universidadRecibidos
    );

  onSelect(universidad: Universidad): void {
    this.universidadSeleccionado = universidad;
  }
}
