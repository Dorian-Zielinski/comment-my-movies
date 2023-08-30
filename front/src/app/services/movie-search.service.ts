import {inject, Injectable} from '@angular/core';
import {Movie} from "../interfaces/movie";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, of, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MovieSearchService {

  constructor(private http: HttpClient) {}

  searchMovies(query: string): Observable<Movie[] | undefined> {
    return this.http.get<Movie[]>('http://localhost:8080/movie/search?query=' + query)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    console.log(error.message);
    if(error.status == 404) {
      return of(undefined);
    } else {
      return throwError(() => new Error('Failed to get movies.'))
    }
  }
}
