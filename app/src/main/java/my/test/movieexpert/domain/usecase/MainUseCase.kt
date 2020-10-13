package my.test.movieexpert.domain.usecase

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import my.test.movieexpert.data.localdatasource.LocalDataSource
import my.test.movieexpert.data.remotedatasource.RemoteDataSource
import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.DataState
import my.test.movieexpert.domain.state.ViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainUseCase @Inject constructor(
    @ApplicationContext context: Context,
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val auth: FirebaseAuth
) {
    fun isUserLoggedIn() = auth.currentUser != null
    fun getCurrentFirebaseUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun userLogout() = auth.signOut()

    suspend fun fetchPopularMovies(page: Int = 1): ViewState<PopularMovie> = try {
        withContext(IO) {
            val remoteData = remoteDataSource.fetchPopularMovies(page)

            if (remoteData is DataState.Success) {
                updateCache(remoteData.listOfMovies)
                ViewState.RemoteData(remoteData.listOfMovies)
            } else {
                when (val cachedData = localDataSource.getAllMovies()) {
                    is DataState.Success -> {
                        ViewState.CachedData(cachedData.listOfMovies)
                    }
                    is DataState.Error -> {
                        ViewState.Error(cachedData.error ?: UNKNOWN_NETWORK_ERROR)
                    }
                    else -> {
                        ViewState.Error(UNKNOWN_NETWORK_ERROR)
                    }
                }
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
        private const val UNKNOWN_NETWORK_ERROR: String = "Connection Failed"
    }
}