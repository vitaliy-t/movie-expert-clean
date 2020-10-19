package my.test.movieexpert.data.remotedatasource.entity

import com.squareup.moshi.Json

data class PopularMovieWebEntity(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "vote_average") val vote_average: Double,
    @field:Json(name = "release_date") val release_date: String,
    @field:Json(name = "poster_path") val poster_path: String?,
)
