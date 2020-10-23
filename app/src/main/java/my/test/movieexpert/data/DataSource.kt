package my.test.movieexpert.data

import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.entity.movie.PopularMovie
import my.test.movieexpert.domain.state.DataState

interface DataSource {
    suspend fun addMovies(listOfMovies: List<PopularMovie>)
    suspend fun removeAllMovies()
    suspend fun getPopularMovies(page: Int = 1): DataState<PopularMovie>
    suspend fun getPopularMovieById(id: Int): DataState<PopularMovie>
    suspend fun getLatestMovie(): DataState<LatestMovie>
}