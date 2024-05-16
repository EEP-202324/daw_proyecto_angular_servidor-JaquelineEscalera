import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UniversityLocationComponent } from '../university-location/university-location.component';
import { UniversityLocation } from '../universitylocation';
import { UniversityService } from '../university.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    UniversityLocationComponent
  ],
  template: `
    <section>
      <form>
        <input type="text" placeholder="Filter by city" #filter>
        <button class="primary" type="button" (click)="filterResults(filter.value)">Search</button>
      </form>
    </section>
    <section class="results">
      <app-university-location
        *ngFor="let universityLocation of filteredLocationList"
        [universityLocation]="universityLocation">
      </app-university-location>
    </section>
  `,
  styleUrls: ['./home.component.css'],
})

export class HomeComponent {
  universityLocationList: UniversityLocation[] = [];
  universityService: UniversityService = inject(UniversityService);
  filteredLocationList: UniversityLocation[] = [];

  constructor() {
    this.universityService.getAllUniversityLocations().then((universityLocationList: UniversityLocation[]) => {
      this.universityLocationList = universityLocationList;
      this.filteredLocationList = universityLocationList;
    });
  }

  filterResults(text: string) {
    if (!text) {
      this.filteredLocationList = this.universityLocationList;
      return;
    }

    this.filteredLocationList = this.universityLocationList.filter(
      universityLocation => universityLocation?.city.toLowerCase().includes(text.toLowerCase())
    );
  }
}
