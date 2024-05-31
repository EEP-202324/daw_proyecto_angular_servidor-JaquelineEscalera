import { Component, OnInit } from '@angular/core';

import { Observable, Subject } from 'rxjs';

import {
   debounceTime, distinctUntilChanged, switchMap
 } from 'rxjs/operators';

import { Universidad } from '../universidad';
import { UniversidadService } from '../universidad.service';

@Component({
  selector: 'app-universidad-search',
  templateUrl: './universidad-search.component.html',
  styleUrl: './universidad-search.component.css'
})
export class UniversidadSearchComponent implements OnInit {
  universidades$!: Observable<Universidad[]>;
  private searchTerms = new Subject<string>();

  constructor(private universidadService: UniversidadService) {}
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.universidades$ = this.searchTerms.pipe(
      debounceTime(300),

      distinctUntilChanged(),

      switchMap((term: string) => this.universidadService.searchHeroes(term)),
    );
  }
}
