package my.test.movieexpert.ui.profilescreen.bindingadapters

import android.view.View
import androidx.databinding.BindingAdapter
import my.test.movieexpert.domain.entity.movie.LatestMovie
import my.test.movieexpert.domain.state.ViewState

object HomeBindingAdapters {

    @BindingAdapter("LatestMovieUiError")
    @JvmStatic
    fun View.noLatestMovieUI(state: ViewState<LatestMovie>?) {
        if (state is ViewState.SingleObject || state is ViewState.Loading) {
            this.visibility = View.GONE
        } else {
            this.visibility = View.VISIBLE
        }
    }

    @BindingAdapter("LatestMovieUI")
    @JvmStatic
    fun View.latestMovieUI(state: ViewState<LatestMovie>?) {
        if (state is ViewState.SingleObject) {
            if (state.data.adult) {
                this.visibility = View.GONE
            } else {
                this.visibility = View.VISIBLE
            }
        } else if (state is ViewState.Loading) {
            this.visibility = View.VISIBLE
        } else {
            this.visibility = View.GONE
        }
    }

}