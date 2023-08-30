import {Component, inject} from '@angular/core';
import { CommonModule } from '@angular/common';
import {FormBuilder, ReactiveFormsModule} from "@angular/forms";
import {MovieSearchService} from "../services/movie-search.service";
import {Movie} from "../interfaces/movie";

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {

  constructor(private formBuilder: FormBuilder) {}

  movieSearchService: MovieSearchService = inject(MovieSearchService);

  searchForm = this.formBuilder.group({
    movieName:''
  })

  movies: Movie[] | undefined = [];

  onSubmit(): void {
    let form = this.searchForm.value;
    this.searchMovie(<string>form.movieName);
  }

  searchMovie(movie: string) {
    this.movieSearchService.searchMovies(movie)
      .subscribe(movies => this.movies = movies);
  }
}
