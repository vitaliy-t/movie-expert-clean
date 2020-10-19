package my.test.movieexpert.ui.profilescreen.view.fragments.subFragments.ofMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import my.test.movieexpert.R
import my.test.movieexpert.databinding.FragmentMoviesPopularBinding
import my.test.movieexpert.domain.state.ViewState
import my.test.movieexpert.ui.profilescreen.view.fragments.subFragments.ofMovies.adapters.PopularMoviesRecyclerAdapter
import my.test.movieexpert.ui.profilescreen.viewModel.MoviesViewModel
import my.test.movieexpert.ui.utilities.AlertDialog
import javax.inject.Inject

@AndroidEntryPoint
class MoviesPopularFragment : Fragment() {

    @Inject
    lateinit var alertDialog: AlertDialog

    private val moviesViewModel: MoviesViewModel by activityViewModels()
    private val adapter = PopularMoviesRecyclerAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMoviesPopularBinding.inflate(inflater, container, false)

        binding.viewModel = moviesViewModel
        binding.popularMoviesRV.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.popularMoviesRV.adapter = adapter

        moviesViewModel.popularMoviesState.observe(viewLifecycleOwner, { state ->
            when (state) {
                is ViewState.RemoteData -> {
                    // TODO: Find a way to deal with this via DataBinding
                    binding.popularMoviesSwipeRefresh.isRefreshing = false
                    binding.progressBarLayout.visibility = View.GONE
                    binding.pageNavigation.visibility = View.VISIBLE

                    adapter.submitList(state.data)
                }
                is ViewState.CachedData -> {
                    // TODO: Find a way to deal with this via DataBinding
                    binding.popularMoviesSwipeRefresh.isRefreshing = false
                    binding.progressBarLayout.visibility = View.GONE
                    binding.pageNavigation.visibility = View.VISIBLE

                    adapter.submitList(state.data)
                    showSnackbar(requireView(), getString(R.string.movies_popular_message_viewing_cached_content), Snackbar.LENGTH_INDEFINITE)
                }
                is ViewState.Error -> {
                    // TODO: Find a way to deal with this via DataBinding
                    binding.popularMoviesSwipeRefresh.isRefreshing = false
                    binding.progressBarLayout.visibility = View.GONE
                    binding.pageNavigation.visibility = View.VISIBLE

                    alertDialog.error(requireContext(), state.error)
                }
            }
        })

        // TODO: Look into implementing this via DataBinding
        binding.popularMoviesSwipeRefresh.setOnRefreshListener {
            binding.popularMoviesSwipeRefresh.isRefreshing = true
            moviesViewModel.fetchPopularMovies()
        }

        return binding.root
    }

    private fun showSnackbar(view: View, message: String, length: Int) {
        val snackbar = Snackbar.make(view, message, length)
        snackbar.animationMode = Snackbar.ANIMATION_MODE_SLIDE
        snackbar.setAction(view.context.getString(R.string.alert_dialog_default_button_ok)) { snackbar.dismiss() }
        snackbar.show()
    }
}