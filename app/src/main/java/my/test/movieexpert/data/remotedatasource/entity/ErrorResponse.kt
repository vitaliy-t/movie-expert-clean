package my.test.movieexpert.data.remotedatasource.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import retrofit2.Response

data class ErrorResponse(
    @field:Json(name = "status_message") val message: String,
    @field:Json(name = "status_code") val code: Int
) {
    companion object {
        fun <T> parse(response: Response<T>): ErrorResponse {
            val moshi = Moshi.Builder().build()
            val adapter: JsonAdapter<ErrorResponse> = moshi.adapter(ErrorResponse::class.java)

            val errorString = response.errorBody()?.string()
            return if (errorString == null) {
                ErrorResponse("", -1)
            } else {
                adapter.fromJson(errorString)!!
            }
        }
    }
}