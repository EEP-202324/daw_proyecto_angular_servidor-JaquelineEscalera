import { Injectable } from '@angular/core';
import { Universidad } from './universidad';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { MensajeService } from './mensaje.service'
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UniversidadService {

  private universidadesUrl = 'api/universidades';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private servicioDeMensajes: MensajeService) { }

  getUniversidades(): Observable<Universidad[]> {
    return this.http.get<Universidad[]>(this.universidadesUrl)
      .pipe(
        tap(_ => this.log('universidades recuperados')),
        catchError(this.handleError<Universidad[]>('getUniversidades', []))
        );
  }

  getUniversidad(id: number): Observable<Universidad> {
    const url = `${this.universidadesUrl}/${id}`;
    return this.http.get<Universidad>(url).pipe(
      tap(_ => this.log(`UniversidadService: recuperado universidad id=${id}`)),
      catchError(this.handleError<Universidad>(`getUniversidadid id=${id}`))
    );
  }

  private handleError<T>(operacion = 'operacion', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      this.log(`${operacion} failed: ${error.mensaje}`);
      return of(result as T);
    }
  }

  /** Log a HeroService message with the MessageService */
  private log(mensaje: string) {
    this.servicioDeMensajes.add(`UniversidadService: ${mensaje}`);
  }
  updateUniversidad(universidad: Universidad): Observable<any> {
    return this.http.put(this.universidadesUrl, universidad, this.httpOptions).pipe(
      tap(_ => this.log(`updated hero id=${universidad.id}`)),
      catchError(this.handleError<any>('updateUniversidad'))
    );
  }
  addUniversidad(universidad: Universidad): Observable<Universidad> {
    return this.http.post<Universidad>(this.universidadesUrl, universidad, this.httpOptions).pipe(
      tap((newUniversidad: Universidad) => this.log(`added universidad w/ id=${newUniversidad.id}`)),
      catchError(this.handleError<Universidad>('addUniversidad'))
    );
  }/** DELETE: delete the hero from the server */
deleteUniversidad(id: number): Observable<Universidad> {
  const url = `${this.universidadesUrl}/${id}`;

  return this.http.delete<Universidad>(url, this.httpOptions).pipe(
    tap(_ => this.log(`deleted universidad id=${id}`)),
    catchError(this.handleError<Universidad>('deleteUniversidad'))
  );
}
/* GET heroes whose name contains search term */
searchUniversidades(term: string): Observable<Universidad[]> {
  if (!term.trim()) {
    // if not search term, return empty hero array.
    return of([]);
  }
  return this.http.get<Universidad[]>(`${this.universidadesUrl}/?name=${term}`).pipe(
    tap(x => x.length ?
       this.log(`found universidades matching "${term}"`) :
       this.log(`no universidades matching "${term}"`)),
    catchError(this.handleError<Universidad[]>('searchHeroes', []))
  );
}
}
