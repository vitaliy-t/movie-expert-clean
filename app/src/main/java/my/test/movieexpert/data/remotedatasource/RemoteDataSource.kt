package my.test.movieexpert.data.remotedatasource

import my.test.movieexpert.data.DataSource
import my.test.movieexpert.data.remotedatasource.api.LatestMovieApi
import my.test.movieexpert.data.remotedatasource.api.PopularMovieApi
import my.test.movieexpert.data.remotedatasource.entity.ErrorResponse
import my.test.movieexpert.data.remotedatasource.mapper.PopularMovieMapper
import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.entity.movie.PopularMovie
import my.test.movieexpert.domain.state.DataState
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val popularMovieApi: PopularMovieApi,
    private val latestMovieApi: LatestMovieApi,
    private val mapper: PopularMovieMapper
) : DataSource {
    override suspend fun addMovies(listOfMovies: List<PopularMovie>) {
        // Empty...
    }

    override suspend fun removeAllMovies() {
        // Empty...
    }

    override suspend fun getPopularMovies(page: Int): DataState<PopularMovie> {
        return try {
            val response = popularMovieApi.getMovies(page = page)
            if (response.isSuccessful) {
                DataState.SuccessList(mapper.mapToListOfPopularMovie(response.body()!!.results))
            } else {
                DataState.Error(ErrorResponse.parse(response).message)
            }
        } catch (error: Throwable) {
            DataState.Error(error.localizedMessage)
        }
    }

    override suspend fun getPopularMovieById(id: Int): DataState<PopularMovie> {
        TODO("Should be empty")
    }

    override suspend fun getLatestMovie(): DataState<LatestMovie> {
        return try {
            val response = latestMovieApi.getMovie()
            if (response.isSuccessful) {
                DataState.SuccessObject(mapper.mapToLatestMovie(response.body()!!))
            } else {
                DataState.Error(ErrorResponse.parse(response).message)
            }
        } catch (error: Throwable) {
            DataState.Error(error.localizedMessage)
        }
    }
}