package my.test.movieexpert.domain.repository

import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.entity.movie.PopularMovie
import my.test.movieexpert.domain.state.ViewState

interface MoviesRepository {
    suspend fun getPopularMovieById(id: Int): PopularMovie?
    suspend fun getPopularMovies(page: Int = 1): ViewState<PopularMovie>
    suspend fun getLatestMovie(): ViewState<LatestMovie>
}