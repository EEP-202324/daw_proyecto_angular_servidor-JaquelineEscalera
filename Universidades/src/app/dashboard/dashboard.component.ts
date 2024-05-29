import { Component, OnInit } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  universidadTop:Universidad[] = [];

  constructor(private servicioDeUniversidades: UniversidadService) {

  }

  ngOnInit(): void {
    this.getUniversidad();
  }

  getUniversidad(): void {
    this.servicioDeUniversidades.getUniversidad().subscribe(
      UniversidadRecibidos => this.universidadTop = UniversidadRecibidos.slice(1, 5)
    );
  }
}
