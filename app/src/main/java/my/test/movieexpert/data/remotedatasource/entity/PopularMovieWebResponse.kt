package my.test.movieexpert.data.remotedatasource.entity

import com.squareup.moshi.Json

data class PopularMovieWebResponse(
    @field:Json(name = "page") val page: Int,
    @field:Json(name = "results") val results: List<PopularMovieWebEntity>,
    @field:Json(name = "total_results") val total_results: Int,
    @field:Json(name = "total_pages") val total_pages: Int,

    @field:Json(name = "status_message") val status_message: String?,
    @field:Json(name = "status_code") val status_code: String?
)
