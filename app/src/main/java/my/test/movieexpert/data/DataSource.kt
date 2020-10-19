package my.test.movieexpert.data

import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.DataState

/**
 * How to approach this in more generic way while maintaining the ability to use Dagger Injection
 *
 * i.e.:
 *      DataSource<PopularMovie> would have getMovies():DataState<PopularMovie>
 *      DataSource<TopRatedMovie> would have getMovies():DataState<TopRatedMovie>
 *          etc...
 */

interface DataSource {
    suspend fun addMovies(listOfMovies: List<PopularMovie>)
    suspend fun removeAllMovies()
    suspend fun getMovies(page: Int = 1): DataState<PopularMovie>
}