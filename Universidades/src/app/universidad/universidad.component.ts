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
      universidadesRecibidos => this.universidades = universidadesRecibidos
    );
  }
}
