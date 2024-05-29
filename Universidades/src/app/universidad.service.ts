import { Injectable } from '@angular/core';
import { UNIVERSIDAD } from './mock-universidades';
import { Universidad } from './universidad';
import { Observable, of } from 'rxjs';
import { MensajeService } from './mensaje.service'

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {

  constructor(private servicioDeMensajes: MensajeService) { }

  getUniversidad(): Observable<Universidad[]> {
    const universidad: Observable<Universidad[]> = of(UNIVERSIDAD)
    this.servicioDeMensajes.add("UniversidadService: universidades recuperadas");
    return universidad;
  }
}
