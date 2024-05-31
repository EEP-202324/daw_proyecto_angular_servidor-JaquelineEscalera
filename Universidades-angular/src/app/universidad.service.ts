import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { UNIVERSIDADES } from './mock-universidades';
import { Observable, of } from 'rxjs';
import { MensajeService } from './mensaje.service';

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {

  constructor(private mensajeService: MensajeService) { }
  getUniversidades(): Observable<Universidad[]> {
    const universidades = of(UNIVERSIDADES);
    this.mensajeService.add('UniversidadService: fetched universidades')
    return universidades;
  }
}
