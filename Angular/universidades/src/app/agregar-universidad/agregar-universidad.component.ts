import { Component } from '@angular/core';
import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-agregar-universidad',
  templateUrl: './agregar-universidad.component.html',
  styleUrl: './agregar-universidad.component.css'
})
export class AgregarUniversidadComponent {
  universidad: Universidad = { nombre: '' } as Universidad
  constructor(private universidadService: UniversidadService, private router: Router) {}

  addUniversidad(): void {
    this.universidadService.addUniversidad(this.universidad).subscribe(() => {
      this.router.navigate(['/universidades']);
    });
  }

  goBack(): void {
    this.router.navigate(['/universidades']);
  }
}
