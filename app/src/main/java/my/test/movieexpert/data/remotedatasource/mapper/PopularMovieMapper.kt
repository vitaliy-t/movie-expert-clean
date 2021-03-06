package my.test.movieexpert.data.remotedatasource.mapper

import my.test.movieexpert.data.remotedatasource.entity.latestmovie.LatestMovieWebEntity
import my.test.movieexpert.data.remotedatasource.entity.popularmovie.PopularMovieWebEntity
import my.test.movieexpert.data.remotedatasource.utilities.TMDB_IMAGE_BASE_URL
import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.entity.movie.PopularMovie
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() {
    private fun mapToPopularMovieWebEntity(entity: PopularMovie): PopularMovieWebEntity {
        return PopularMovieWebEntity(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            vote_average = entity.voteAverage,
            release_date = entity.releaseDate,
            poster_path = entity.posterUrl
        )
    }

    private fun mapToPopularMovie(entity: PopularMovieWebEntity): PopularMovie {
        return PopularMovie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            voteAverage = entity.vote_average,
            releaseDate = entity.release_date,
            posterUrl = TMDB_IMAGE_BASE_URL + entity.poster_path
        )
    }

    fun mapToLatestMovie(entity: LatestMovieWebEntity): LatestMovie {
        return LatestMovie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            voteAverage = entity.vote_average,
            releaseDate = entity.release_date,
            posterUrl = TMDB_IMAGE_BASE_URL + entity.poster_path,
            adult = entity.adult,
            originalLang = entity.originalLang,
            originalTitle = entity.originalTitle,
            budget = entity.budget,
            revenue = entity.revenue,
            runtime = entity.runtime,
            popularity = entity.popularity,
            status = entity.status
        )
    }

    fun mapToListOfPopularMovieWebEntity(list: List<PopularMovie>): List<PopularMovieWebEntity> {
        val newList = mutableListOf<PopularMovieWebEntity>()

        for (movie in list) {
            newList.add(mapToPopularMovieWebEntity(movie))
        }

        return newList
    }

    fun mapToListOfPopularMovie(list: List<PopularMovieWebEntity>): List<PopularMovie> {
        val newList = mutableListOf<PopularMovie>()

        for (movie in list) {
            newList.add(mapToPopularMovie(movie))
        }

        return newList
    }
}