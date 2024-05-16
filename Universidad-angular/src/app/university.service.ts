import { Injectable } from '@angular/core';
import { UniversityLocation } from './universitylocation';

@Injectable({
  providedIn: 'root'
})
export class UniversityService {

  url = 'http://localhost:3000/universities';

  async getAllUniversityLocations(): Promise<UniversityLocation[]> {
    const data = await fetch(this.url);
    return await data.json() ?? [];
  }

  async getUniversityLocationById(id: number): Promise<UniversityLocation | undefined> {
    const data = await fetch(`${this.url}/${id}`);
    return await data.json() ?? {};
  }

  submitApplication(firstName: string, lastName: string, email: string) {
    console.log(firstName, lastName, email);
  }
}
