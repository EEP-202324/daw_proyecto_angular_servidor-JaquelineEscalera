import { Component } from '@angular/core';
import { UniversityService } from '../university.service';
import { UniversityLocation } from '../universitylocation';

@Component({
  selector: 'app-home',
  template: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {
  universityLocationList: UniversityLocation[] = [];
  filteredLocationList: UniversityLocation[] = [];

  constructor(private universityService: UniversityService) {
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
