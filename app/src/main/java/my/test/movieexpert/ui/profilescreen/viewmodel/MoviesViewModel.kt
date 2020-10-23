package my.test.movieexpert.ui.profilescreen.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import my.test.movieexpert.domain.entity.movie.PopularMovie
import my.test.movieexpert.domain.state.ViewState
import my.test.movieexpert.domain.usecase.MainUseCase

class MoviesViewModel @ViewModelInject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _popularMoviesState = MutableLiveData<ViewState<PopularMovie>>()
    val popularMoviesState: LiveData<ViewState<PopularMovie>>
        get() = _popularMoviesState

    private val _popularMovieById = MutableLiveData<PopularMovie?>()
    val popularMovieById: LiveData<PopularMovie?>
        get() = _popularMovieById

    private var currentPage: Int = 1

    init {
        fetchPopularMovies()
    }

    fun fetchPopularMovieById(id: Int) {
        _popularMovieById.value = null
        viewModelScope.launch {
            _popularMovieById.value = mainUseCase.getPopularMovieById(id)
        }
    }

    fun fetchPopularMovies(page: Int = currentPage) {
        viewModelScope.launch {
            _popularMoviesState.value = mainUseCase.getPopularMovies(page = page)
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