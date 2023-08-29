import {inject, Injectable} from '@angular/core';
import {Movie} from "../interfaces/movie";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MovieSearchService {

  constructor(private http: HttpClient) {}

  searchMovies(query: string): Observable<Movie[]> {
    return this.http.get<Movie[]>('http://localhost:8080/movie/search?query=' + query)
      .pipe(
        catchError(this.handleError)
      );
  }

  private handleError(error: HttpErrorResponse) {
    if(error.status == 0) {
      // A client-side or network error occurred. Handle it accordingly.
      console.error('An error occured', error.error);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong.
      console.error(`Backend returned code ${error.status},  body was: `, error.error);
    }

    // Return an observable with a user-facing error message.
    return throwError(() => new Error('Something bad happened; please try again later.'));
  }
}
