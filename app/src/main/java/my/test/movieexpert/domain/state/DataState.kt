package my.test.movieexpert.domain.state

sealed class DataState<out T> {
    data class SuccessList<T>(val listOfData: List<T>) : DataState<T>()
    data class SuccessObject<T>(val data: T) : DataState<T>()
    data class Error<T>(val error: String?) : DataState<T>()
}
