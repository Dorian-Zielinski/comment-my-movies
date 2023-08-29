import { TestBed } from '@angular/core/testing';

import { MovieSearchService } from './movie-search.service';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Movie} from "../interfaces/movie";
import {of} from "rxjs";

describe('MovieSearchService', () => {
  let service: MovieSearchService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get']);
    service = new MovieSearchService(httpClientSpy);
    /*TestBed.configureTestingModule({});
    service = TestBed.inject(MovieSearchService);*/
  });

  it('should return expected movies (HttpClient called once)', (done: DoneFn) => {
    const expectedMovies: Movie[] = [{id:"1", title: "Batman Begins", original_language :"en"},
      {id:"2", title: "Batman the Dark Knight", original_language :"en"}];

    httpClientSpy.get.and.returnValue(of(expectedMovies));

    service.searchMovies("Batman").subscribe({
      next: movies => {
        expect(movies)
          .withContext('expected movies')
          .toEqual(expectedMovies);
        done();
      },
      error: done.fail
    });
    expect(httpClientSpy.get.calls.count())
      .withContext('one call')
      .toBe(1);
  });

  it('should return an error when the server returns a 404', (done: DoneFn) => {
    const errorResponse = new HttpErrorResponse({
      error: 'test 404 error',
      status: 404, statusText: 'Not Found'
    })

    httpClientSpy.get.and.returnValue(of(errorResponse));

    service.searchMovies('Batman').subscribe({
      next: movies => done.fail('expected an error, not movies'),
      error: error => {
        expect(error.message).toEqual('Something bad happened; please try again later.')
        done();
      }
    })
  });
});
