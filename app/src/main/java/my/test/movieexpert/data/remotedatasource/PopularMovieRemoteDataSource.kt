package my.test.movieexpert.data.remotedatasource

import my.test.movieexpert.data.remotedatasource.api.PopularMovieApi
import my.test.movieexpert.data.remotedatasource.mapper.PopularMovieMapper
import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.DataState
import javax.inject.Inject

class PopularMovieRemoteDataSource @Inject constructor(
    private val popularMovieApi: PopularMovieApi,
    private val mapper: PopularMovieMapper
) : RemoteDataSource {
    override suspend fun fetchPopularMovies(page: Int): DataState<PopularMovie> {
        return try {
            val response = popularMovieApi.getMovies(page = page)
            if (response.code() == RESPONSE_CODE_SUCCESS) {
                DataState.Success(mapper.mapToListOfPopularMovie(response.body()?.results ?: listOf()))
            } else {
                DataState.Error(response.body()?.status_message)
            }
        } catch (error: Throwable) {
            DataState.Error(error.localizedMessage)
        }
    }

    companion object {
        private const val RESPONSE_CODE_SUCCESS: Int = 200
    }
}