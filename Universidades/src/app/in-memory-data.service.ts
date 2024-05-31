import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Universidad } from './universidad';

@Injectable({
  providedIn: 'root',
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const universidades = [
      {id: 1, nombre: 'Universidad Complutense de Madrid' },
      { id: 2, nombre: 'Universidad Autonoma de Madrid' },
      { id: 3, nombre: 'Universidad Politecnica de Madrid' },
      { id: 4, nombre: 'Universidad Camilo Jose Cela' },
      { id: 5, nombre: 'Universidad Carlos III DE Madrid' },
      { id: 6, nombre: 'Universidad San Pablo=CEU' },
    ];
    return {universidades};
  }

  // Overrides the genId method to ensure that a hero always has an id.
  // If the heroes array is empty,
  // the method below returns the initial number (11).
  // if the heroes array is not empty, the method below returns the highest
  // hero id + 1.
  genId(universidades: Universidad[]): number {
    return universidades.length > 0 ? Math.max(...universidades.map(universidad => universidad.id)) + 1 : 11;
  }
}
