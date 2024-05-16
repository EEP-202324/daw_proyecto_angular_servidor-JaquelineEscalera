import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { UniversityService } from '../university.service';
import { UniversityLocation } from '../universitylocation';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';

@Component({
  selector: 'app-details',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  template: `
    <article>
      <img class="university-photo" [src]="universityLocation?.photo"
        alt="Exterior photo of {{universityLocation?.name}}"/>
      <section class="university-description">
        <h2 class="university-heading">{{universityLocation?.name}}</h2>
        <p class="university-location">{{universityLocation?.city}}, {{universityLocation?.state}}</p>
      </section>
      <section class="university-features">
        <h2 class="section-heading">About this university location</h2>
        <ul>
          <li>Faculties available: {{universityLocation?.faculties}}</li>
          <li>Does this university have wifi: {{universityLocation?.wifi}}</li>
          <li>Does this university have library: {{universityLocation?.library}}</li>
        </ul>
      </section>
      <section class="university-apply">
        <h2 class="section-heading">Apply now to enroll</h2>
        <form [formGroup]="applyForm" (submit)="submitApplication()">
          <label for="first-name">First Name</label>
          <input id="first-name" type="text" formControlName="firstName">

          <label for="last-name">Last Name</label>
          <input id="last-name" type="text" formControlName="lastName">

          <label for="email">Email</label>
          <input id="email" type="email" formControlName="email">
          <button type="submit" class="primary">Apply now</button>
        </form>
      </section>
    </article>
  `,
  styleUrls: ['./details.component.css'],
})
export class DetailsComponent {

  route: ActivatedRoute = inject(ActivatedRoute);
  universityService = inject(UniversityService);
  universityLocation: UniversityLocation | undefined;

  applyForm = new FormGroup({
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    email: new FormControl('')
  });

  constructor() {
    const universityLocationId = parseInt(this.route.snapshot.params['id'], 10);
    this.universityService.getUniversityLocationById(universityLocationId).then(universityLocation => {
      this.universityLocation = universityLocation;
    });
  }

  submitApplication() {
    this.universityService.submitApplication(
      this.applyForm.value.firstName ?? '',
      this.applyForm.value.lastName ?? '',
      this.applyForm.value.email ?? ''
    );
  }

}
