package my.test.movieexpert.domain.state

sealed class DataState<out T> {
    data class Success<T>(val listOfMovies: List<T>) : DataState<T>()
    data class Error<T>(val error: String?) : DataState<T>()
}
