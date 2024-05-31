import { Component, OnInit  } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

import {Universidad} from '../universidad';
import { UniversidadService } from '../universidad.service';


@Component({
  selector: 'app-universidad-detail',
  templateUrl: './universidad-detail.component.html',
  styleUrl: './universidad-detail.component.css'
})
export class UniversidadDetailComponent implements OnInit {
  universidad: Universidad | undefined;

  constructor(
    private route: ActivatedRoute,
    private universidadService: UniversidadService,
    private location: Location
  ) {}

  ngOnInit(): void {
    this.getUniversidad();
  }

  getUniversidad(): void {
    const id = parseInt(this.route.snapshot.paramMap.get('id')!, 10);
    this.universidadService.getUniversidad(id).subscribe(
      universidad => this.universidad = universidad);
  }
  goBack(): void {
    this.location.back();
  }
  save(): void {
    if (this.universidad) {
      this.universidadService.updateUniversidad(this.universidad)
        .subscribe(() => this.goBack());
    }
  }
 // add(name: string): void {
  //  name = name.trim();
   // if (!name) { return; }
   // this.universidadService.addUniversidad({ nombre } as Universidad)
   //   .subscribe(universidad => {
  //      this.universidades.push(universidad);
  //    });
  //}
}
