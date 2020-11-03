package my.test.movieexpert.data.localdatasource

import my.test.movieexpert.data.DataSource
import my.test.movieexpert.data.localdatasource.dao.PopularMovieDao
import my.test.movieexpert.data.localdatasource.mapper.PopularMovieMapper
import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.entity.movie.PopularMovie
import my.test.movieexpert.domain.state.DataState
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val popularMovieDao: PopularMovieDao,
    private val mapper: PopularMovieMapper
) : DataSource {
    override suspend fun addMovies(listOfMovies: List<PopularMovie>) {
        popularMovieDao.addMovies(mapper.mapToListOfPopularMovieRoomEntity(listOfMovies))
    }

    override suspend fun removeAllMovies() {
        popularMovieDao.clearTable()
    }

    override suspend fun getPopularMovies(page: Int): DataState<PopularMovie> {
        val cache = popularMovieDao.getMovies()
        cache?.let { return DataState.SuccessList(mapper.mapToListOfPopularMovie(cache)) }
        return DataState.Error(CACHE_IS_EMPTY)
    }

    override suspend fun getPopularMovieById(id: Int): DataState<PopularMovie> {
        val movie = popularMovieDao.getMovieById(id)
        return DataState.SuccessObject(mapper.mapToPopularMovie(movie))
    }

    override suspend fun getLatestMovie(): DataState<LatestMovie> {
        TODO("Should be empty")
    }

    companion object {
        private const val CACHE_IS_EMPTY: String = "Cache is empty"
    }
}