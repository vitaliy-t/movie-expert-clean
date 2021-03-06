package my.test.movieexpert.domain.repository

import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import my.test.movieexpert.data.DataSource
import my.test.movieexpert.data.di.Local
import my.test.movieexpert.data.di.Remote
import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.entity.movie.PopularMovie
import my.test.movieexpert.domain.state.DataState
import my.test.movieexpert.domain.state.ViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesRepositoryImpl @Inject constructor(
    @Local private val localDataSource: DataSource,
    @Remote private val remoteDataSource: DataSource,
) : MoviesRepository {
    override suspend fun getPopularMovieById(id: Int) = try {
        withContext(IO) {
            val movie = localDataSource.getPopularMovieById(id) as DataState.SuccessObject
            movie.data
        }
    } catch (error: Throwable) {
        null
    }

    override suspend fun getPopularMovies(page: Int): ViewState<PopularMovie> = try {
        withContext(IO) {
            val remoteData = remoteDataSource.getPopularMovies(page)

            if (remoteData is DataState.SuccessList) {
                updateCache(remoteData.listOfData)
                ViewState.RemoteData(remoteData.listOfData)
            } else {
                val localData = localDataSource.getPopularMovies()
                if (localData is DataState.SuccessList) {
                    ViewState.CachedData(localData.listOfData)
                } else {
                    ViewState.Error(UNKNOWN_NETWORK_ERROR)
                }
            }
        }
    } catch (error: Throwable) {
        ViewState.Error(error.localizedMessage ?: UNKNOWN_NETWORK_ERROR)
    }

    override suspend fun getLatestMovie(): ViewState<LatestMovie> = try {
        withContext(IO) {
            val remoteData = remoteDataSource.getLatestMovie()
            if (remoteData is DataState.SuccessObject) {
                ViewState.SingleObject(remoteData.data)
            } else {
                ViewState.Error((remoteData as DataState.Error).error ?: UNKNOWN_NETWORK_ERROR)
            }
        }
    } catch (error: Throwable) {
        ViewState.Error(error.localizedMessage ?: UNKNOWN_NETWORK_ERROR)
    }

    private suspend fun updateCache(list: List<PopularMovie>) {
        localDataSource.removeAllMovies()
        localDataSource.addMovies(list)
    }

    companion object {
        private const val UNKNOWN_NETWORK_ERROR: String = "Unknown Error"
    }
}