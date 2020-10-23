package my.test.movieexpert.ui.profilescreen.viewmodel

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import my.test.movieexpert.R
import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.state.ViewState
import my.test.movieexpert.domain.usecase.MainUseCase

class HomeViewModel @ViewModelInject constructor(
    @ApplicationContext private val context: Context,
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _latestMovieState = MutableLiveData<ViewState<LatestMovie>>()
    val latestMovieState: LiveData<ViewState<LatestMovie>>
        get() = _latestMovieState

    val latestMovie = MediatorLiveData<LatestMovie>().apply {
        addSource(_latestMovieState) {
            value = if (it is ViewState.SingleObject) prepLatestMovieForDisplay(it.data) else null
        }
    }

    init {
        getLatestMovie()
    }

    fun getLatestMovie() {
        _latestMovieState.value = ViewState.Loading
        viewModelScope.launch {
            _latestMovieState.value = mainUseCase.getLatestMovie()
        }
    }

    private fun prepLatestMovieForDisplay(latestMovie: LatestMovie): LatestMovie {
        return LatestMovie(
            id = latestMovie.id,
            title = context.getString(R.string.home_latest_movie_title, latestMovie.title),
            overview = context.getString(R.string.home_latest_movie_overview, latestMovie.overview),
            voteAverage = context.getString(R.string.home_latest_movie_rating, latestMovie.voteAverage),
            releaseDate = context.getString(R.string.home_latest_movie_release_date, latestMovie.releaseDate),
            posterUrl = latestMovie.posterUrl,
            adult = latestMovie.adult,
            originalLang = context.getString(R.string.home_latest_movie_original_language, latestMovie.originalLang),
            originalTitle = context.getString(R.string.home_latest_movie_original_Title, latestMovie.originalTitle),
            budget = context.getString(R.string.home_latest_movie_budget, latestMovie.budget),
            revenue = context.getString(R.string.home_latest_movie_revenue, latestMovie.revenue),
            runtime = context.getString(R.string.home_latest_movie_runtime, latestMovie.runtime),
            popularity = context.getString(R.string.home_latest_movie_popularity, latestMovie.popularity),
            status = context.getString(R.string.home_latest_movie_status, latestMovie.status)
        )
    }
}
