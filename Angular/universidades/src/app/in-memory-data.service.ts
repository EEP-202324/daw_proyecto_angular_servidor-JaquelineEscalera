import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api';
import { Universidad } from './universidad';

@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const heroes = [
      { id: 12, nombre: 'A', gmail: 'gmailA', numero: '1A' },
      { id: 13, nombre: 'B', gmail: 'gmailB', numero: '2B' },
      { id: 14, nombre: 'C', gmail: 'gmailC', numero: '3C' }
    ];
    return {heroes};
  }

  genId(universidades: Universidad[]): number {
    return universidades.length > 0 ? Math.max(...universidades.map(universidad => universidad.id)) + 1 : 11;
  }

}
