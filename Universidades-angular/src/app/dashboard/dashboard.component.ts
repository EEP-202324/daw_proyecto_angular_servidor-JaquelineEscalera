import { Component, OnInit } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit {
  universidades: Universidad[] = [];

  constructor(private universidadService: UniversidadService) { }

  ngOnInit(): void {
    this.getUniversidades();
  }

  getUniversidades(): void {
    this.universidadService.getUniversidades()
      .subscribe(universidades => this.universidades = universidades.slice(1, 5));
  }
}
