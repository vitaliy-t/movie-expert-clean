package my.test.movieexpert.domain.usecase

import my.test.movieexpert.domain.repository.MainRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    fun isUserLoggedIn() = mainRepository.isUserLoggedIn()
    fun getCurrentFirebaseUser() = mainRepository.getCurrentFirebaseUser()

    fun userLogout() = mainRepository.userLogout()

    suspend fun getPopularMovieById(id: Int) = mainRepository.getPopularMovieById(id)
    suspend fun getPopularMovies(page: Int = 1) = mainRepository.getPopularMovies(page)
    suspend fun getLatestMovie() = mainRepository.getLatestMovie()
}