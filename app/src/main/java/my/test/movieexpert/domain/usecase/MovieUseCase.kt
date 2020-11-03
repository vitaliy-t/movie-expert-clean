package my.test.movieexpert.domain.usecase

import my.test.movieexpert.domain.repository.MoviesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieUseCase @Inject constructor(
    private val mainRepository: MoviesRepository
) {
    suspend fun getPopularMovieById(id: Int) = mainRepository.getPopularMovieById(id)
    suspend fun getPopularMovies(page: Int = 1) = mainRepository.getPopularMovies(page)
    suspend fun getLatestMovie() = mainRepository.getLatestMovie()
}