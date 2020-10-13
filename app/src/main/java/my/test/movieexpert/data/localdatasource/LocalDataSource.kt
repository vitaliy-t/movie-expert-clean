package my.test.movieexpert.data.localdatasource

import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.DataState

interface LocalDataSource {
    suspend fun addMovies(listOfMovies: List<PopularMovie>)
    suspend fun removeAllMovies()
    suspend fun getAllMovies(): DataState<PopularMovie>

}