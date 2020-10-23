package my.test.movieexpert.domain.state

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class SingleObject<T>(val data: T) : ViewState<T>()
    data class RemoteData<T>(val data: List<T>) : ViewState<T>()
    data class CachedData<T>(val data: List<T>) : ViewState<T>()
    data class Error<T>(val error: String) : ViewState<T>()
}