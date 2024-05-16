import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UniversityLocation } from '../universitylocation';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-university-location',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule
  ],
  template: `
    <section class="listing">
      <img class="listing-photo" [src]="universityLocation.photo" alt="Exterior photo of {{universityLocation.name}}">
      <h2 class="listing-heading">{{ universityLocation.name }}</h2>
      <p class="listing-location">{{ universityLocation.city}}, {{universityLocation.state }}</p>
      <a [routerLink]="['/details', universityLocation.id]">Learn More</a>
    </section>
  `,
  styleUrls: ['./housing-location.component.css'],
})

export class UniversityLocationComponent {

  @Input() universityLocation!: UniversityLocation;

}
