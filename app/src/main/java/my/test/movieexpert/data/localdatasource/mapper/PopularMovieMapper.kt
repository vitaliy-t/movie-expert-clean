package my.test.movieexpert.data.localdatasource.mapper

import my.test.movieexpert.data.localdatasource.entity.PopularMovieRoomEntity
import my.test.movieexpert.domain.entity.PopularMovie
import javax.inject.Inject

class PopularMovieMapper @Inject constructor() {
    private fun mapToPopularMovieRoomEntity(entity: PopularMovie): PopularMovieRoomEntity {
        return PopularMovieRoomEntity(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            vote_average = entity.vote_average,
            release_date = entity.release_date,
            poster_path = entity.poster_path
        )
    }

    private fun mapToPopularMovie(entity: PopularMovieRoomEntity): PopularMovie {
        return PopularMovie(
            id = entity.id,
            title = entity.title,
            overview = entity.overview,
            vote_average = entity.vote_average,
            release_date = entity.release_date,
            poster_path = entity.poster_path
        )
    }

    fun mapToListOfPopularMovieRoomEntity(list: List<PopularMovie>): List<PopularMovieRoomEntity> {
        val newList = mutableListOf<PopularMovieRoomEntity>()

        for (movie in list) {
            newList.add(mapToPopularMovieRoomEntity(movie))
        }

        return newList
    }

    fun mapToListOfPopularMovie(list: List<PopularMovieRoomEntity>): List<PopularMovie> {
        val newList = mutableListOf<PopularMovie>()

        for (movie in list) {
            newList.add(mapToPopularMovie(movie))
        }

        return newList
    }
}