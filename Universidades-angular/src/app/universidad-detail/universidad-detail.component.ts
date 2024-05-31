import { Component,Input } from '@angular/core';
import { Universidad } from '../universidad';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-universidad-detail',
  templateUrl: './universidad-detail.component.html',
  styleUrl: './universidad-detail.component.css'
})
export class UniversidadDetailComponent {
  @Input() universidad?: Universidad;

  constructor(
    private route: ActivatedRoute,
    private universidadService: UniversidadService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getUniversidad();
  }

  getUniversidad(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.universidadService.getUniversidad(id)
      .subscribe(universidad => this.universidad = universidad);
  }
  goBack(): void {
    this.location.back();
  }
}
