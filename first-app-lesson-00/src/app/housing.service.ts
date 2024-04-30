import { Injectable } from '@angular/core';
import { HousingLocation } from './housinglocation';
import { HttpClient } from '@angular/common/http';
import { Peticion } from './peticion';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HousingService {

  url = 'http://localhost:3000/locations';

  constructor(private http: HttpClient) { }

  getCasitas() {
    return this.http.get<HousingLocation[]>(this.url);
  }

  getCasita(id: number) {
    return this.http.get<HousingLocation>(`${this.url}/${id}`);
  }

  /*async getAllHousingLocations(): Promise<HousingLocation[]> {
    const data = await fetch(this.url); /// Mejor HttpClient
    return await data.json() ?? [];
  }
  async getHousingLocationById(id: number): Promise<HousingLocation | undefined> {
    const data = await fetch(`${this.url}/${id}`); /// Mejor HttpClient
    return await data.json() ?? []; // url + "/" + id
  }*/

  submitApplication(nombre: string, apellido: string, email: string) {
    alert(`Petición de: ${nombre} ${apellido} (${email})`);
  }

  enviarPeticion(peticion: Peticion): Observable<Peticion> {
    alert("peticion recibida");
    return this.http.post<Peticion>("urlPendiente", peticion);
  }
}
