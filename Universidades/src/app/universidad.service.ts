import { Injectable } from '@angular/core';
import { UNIVERSIDAD } from './mock-universidades';
import { Universidad } from './universidad';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {

  constructor() { }
  getUniversidad(): Observable<Universidad[]> {
    const universidad: Observable<Universidad[]> = of(UNIVERSIDAD)
    return universidad;
  }
}
