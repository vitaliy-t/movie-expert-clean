package my.test.movieexpert.ui.profilescreen.viewModel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.domain.state.ViewState
import my.test.movieexpert.domain.usecase.MainUseCase

class MoviesViewModel @ViewModelInject constructor(
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