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

  // Push a search term into the observable stream.
  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.universidades$ = this.searchTerms.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.universidadService.searchUniversidades(term)),
    );
  }
  
}
