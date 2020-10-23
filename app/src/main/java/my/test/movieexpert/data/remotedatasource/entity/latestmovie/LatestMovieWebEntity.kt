package my.test.movieexpert.data.remotedatasource.entity.latestmovie

import com.squareup.moshi.Json

data class LatestMovieWebEntity(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "overview") val overview: String,
    @field:Json(name = "vote_average") val vote_average: String,
    @field:Json(name = "release_date") val release_date: String,
    @field:Json(name = "poster_path") val poster_path: String?,
    @field:Json(name = "adult") val adult: Boolean,
    @field:Json(name = "original_language") val originalLang: String,
    @field:Json(name = "original_title") val originalTitle: String,
    @field:Json(name = "budget") val budget: String,
    @field:Json(name = "revenue") val revenue: String,
    @field:Json(name = "runtime") val runtime: String,
    @field:Json(name = "popularity") val popularity: String,
    @field:Json(name = "status") val status: String,
)