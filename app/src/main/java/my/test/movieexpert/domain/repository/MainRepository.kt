package my.test.movieexpert.domain.repository

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import my.test.movieexpert.data.DataSource
import my.test.movieexpert.domain.di.Local
import my.test.movieexpert.domain.di.Remote
import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.DataState
import my.test.movieexpert.domain.state.ViewState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository @Inject constructor(
    @Local private val localDataSource: DataSource,
    @Remote private val remoteDataSource: DataSource,
    private val auth: FirebaseAuth
) {

    /**
     * Remember to look in my personal notes for further thoughts on this implementation in current scenario
     */
    private var isDirty = true

    fun isUserLoggedIn() = auth.currentUser != null
    fun getCurrentFirebaseUser(): FirebaseUser? {
        return auth.currentUser
    }

    fun userLogout() = auth.signOut()

    suspend fun getPopularMovies(page: Int = 1): ViewState<PopularMovie> = try {
        withContext(Dispatchers.IO) {
            if (isDirty) {
                val remoteData = remoteDataSource.getMovies(page)

                if (remoteData is DataState.Success) {
                    updateCache(remoteData.listOfMovies)
                    ViewState.RemoteData(remoteData.listOfMovies)
                } else {
                    val localData = localDataSource.getMovies()
                    if (localData is DataState.Success) {
                        ViewState.CachedData(localData.listOfMovies)
                    } else {
                        ViewState.Error(UNKNOWN_NETWORK_ERROR)
                    }
                }
            } else {
                val localData = localDataSource.getMovies()
                if (localData is DataState.Success) {
                    ViewState.CachedData(localData.listOfMovies)
                } else {
                    ViewState.Error(UNKNOWN_NETWORK_ERROR)
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