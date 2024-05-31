import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-universidades',
  templateUrl: './universidades.component.html',
  styleUrl: './universidades.component.css'
})
export class UniversidadesComponent {
 universidades: Universidad[] = [];

  constructor(private universidadService: UniversidadService) {}

  ngOnInit(): void {
    this.getUniversidades();
  }

  getUniversidades(): void {
  this.universidadService.getUniversidades()
      .subscribe(universidades => this.universidades = universidades);
  }
}
