package my.test.movieexpert.data.localdatasource

import my.test.movieexpert.data.localdatasource.dao.PopularMovieDao
import my.test.movieexpert.data.localdatasource.mapper.PopularMovieMapper
import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.DataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PopularMovieLocalDataSource @Inject constructor(
    private val popularMovieDao: PopularMovieDao,
    private val mapper: PopularMovieMapper
) : LocalDataSource {
    override suspend fun addMovies(listOfMovies: List<PopularMovie>) {
        popularMovieDao.addMovies(mapper.mapToListOfPopularMovieRoomEntity(listOfMovies))
    }

    override suspend fun removeAllMovies() {
        popularMovieDao.clearTable()
    }

    override suspend fun getAllMovies(): DataState<PopularMovie> {
        val cache = popularMovieDao.getMovies()
        cache?.let { return DataState.Success(mapper.mapToListOfPopularMovie(cache)) }
        return DataState.Error(CACHE_IS_EMPTY)
    }

    companion object {
        private const val CACHE_IS_EMPTY: String = "Cache is empty"
    }
}