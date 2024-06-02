import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { Observable, of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {

  private universidadesUrl = 'http://localhost:8080/universidades';  // URL to web api
  constructor(
    private http: HttpClient,
    private messageService: MessageService) { }

    httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };

  getUniversidades(): Observable<Universidad[]> {
    return this.http.get<Universidad[]>(this.universidadesUrl)
    .pipe(
      tap(_ => this.log('fetched universidades')),
      catchError(this.handleError<Universidad[]>('getUniversidades', []))
    );
  }

  getUniversidad(id: number): Observable<Universidad> {
      const url = `${this.universidadesUrl}/${id}`;
    return this.http.get<Universidad>(url).pipe(
      tap(_ => this.log(`fetched universidad id=${id}`)),
      catchError(this.handleError<Universidad>(`getUniversidad id=${id}`))
    );
  }

  /** PUT: update the hero on the server */
  updateUniversidad(universidad: Universidad): Observable<any> {
    const url = `${this.universidadesUrl}/${universidad.id}`;
    return this.http.put<Universidad>(url, universidad, this.httpOptions).pipe(
      tap(_ => this.log(`updated universidad id=${universidad.id}`)),
      catchError(this.handleError<any>('updateUniversidad'))
    );
  }

  /** POST: add a new hero to the server */
  addUniversidad(universidad: Universidad): Observable<Universidad> {
    return this.http.post<Universidad>(this.universidadesUrl, universidad, this.httpOptions).pipe(
      tap((newUniversidad: Universidad) => this.log(`added universidad w/ id=${newUniversidad.id}`)),
      catchError(this.handleError<Universidad>('addHero'))
    );
  }

  /** DELETE: delete the hero from the server */
  deleteUniversidad(id: number): Observable<Universidad> {
    const url = `${this.universidadesUrl}/${id}`;

    return this.http.delete<Universidad>(url, this.httpOptions).pipe(
      tap(_ => this.log(`deleted universidad id=${id}`)),
      catchError(this.handleError<Universidad>('deleteUniversidad'))
    );
  }

  /* GET heroes whose name contains search term */
  searchHeroes(term: string): Observable<Universidad[]> {
    if (!term.trim()) {
      // if not search term, return empty hero array.
      return of([]);
    }
    return this.http.get<Universidad[]>(`${this.universidadesUrl}/?name=${term}`).pipe(
      tap(x => x.length ?
        this.log(`found universidades matching "${term}"`) :
        this.log(`no universidades matching "${term}"`)),
      catchError(this.handleError<Universidad[]>('searchUniversidades', []))
    );
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`UniversidadService: ${message}`);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);

      this.log(`${operation} failed: ${error.message}`);

      return of(result as T);
    };
  }

}
