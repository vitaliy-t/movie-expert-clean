package my.test.movieexpert.data.remotedatasource.entity.popularmovie

import com.squareup.moshi.Json

data class PopularMovieWebResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<PopularMovieWebEntity>,
    @field:Json(name = "total_results") val total_results: Int,
    @field:Json(name = "total_pages") val total_pages: Int,
)
