package my.test.movieexpert.ui.profilescreen.viewModel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.ViewState
import my.test.movieexpert.domain.usecase.MainUseCase

class MoviesViewModel @ViewModelInject constructor(
    @ApplicationContext context: Context,
    private val mainRepository: MainUseCase
) : ViewModel() {

    private val _popularMoviesState = MutableLiveData<ViewState<PopularMovie>>()
    val popularMoviesState: LiveData<ViewState<PopularMovie>>
        get() = _popularMoviesState

    private var currentPage: Int = 1

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovies(page: Int = currentPage) {
        viewModelScope.launch {
            val result = mainRepository.fetchPopularMovies(page = page)
            _popularMoviesState.value = result

//            _popularMoviesState.value = when (result) {
//
//                is PopularMoviesState.Loading -> PopularMoviesState.Loading
//                is PopularMoviesState.Content -> {
//                    //TODO: Perhaps find a way to work with pages in a better, more isolated way.
//                    currentPage = result.response.page
//                    PopularMoviesState.Content(result.response)
//                }
//                is PopularMoviesState.Failure -> {
//                    PopularMoviesState.Failure(result.errorMessage, result.cachedListOfMovies)
//                }

        }
    }

    fun nextPage() {
        if (currentPage != 500) {
            fetchPopularMovies(currentPage + 1)
            currentPage++
        } else {
            fetchPopularMovies()
        }

    }

    fun previousPage() {
        if (currentPage > 1) {
            fetchPopularMovies(currentPage - 1)
            currentPage--
        } else {
            fetchPopularMovies()
        }
    }
}