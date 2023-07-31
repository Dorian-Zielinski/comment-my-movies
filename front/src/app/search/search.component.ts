import {Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import {MovieSearchService} from "../services/movie-search.service";
import {Movie} from "../interfaces/movie";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  movieSearchService: MovieSearchService = inject(MovieSearchService);

  movies: Movie[] = [];

  searchMovie(movie: string) {
    this.movieSearchService.searchMovies(movie)
      .subscribe(movies => this.movies = movies);
  }
}
