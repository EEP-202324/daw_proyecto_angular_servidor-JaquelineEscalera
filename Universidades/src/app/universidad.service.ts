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

  getUniversidades(): Observable<Universidad[]> {
    const universidad = of(UNIVERSIDAD);
    this.servicioDeMensajes.add('UniversidadService: fetched universidad');
    return universidad;
  }

  getUniversidad(id: number): Observable<Universidad> {
    const universidad = UNIVERSIDAD.find(h => h.id === id)!;
    this.servicioDeMensajes.add(`UnivesidadService: fetched universidad id=${id}`);
    return of(universidad);
  }
}
