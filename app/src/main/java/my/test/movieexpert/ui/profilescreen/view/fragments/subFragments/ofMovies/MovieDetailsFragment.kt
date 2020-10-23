package my.test.movieexpert.ui.profilescreen.view.fragments.subFragments.ofMovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import my.test.movieexpert.databinding.FragmentMovieDetailsBinding
import my.test.movieexpert.ui.profilescreen.viewmodel.MoviesViewModel

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()
    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = moviesViewModel

        moviesViewModel.fetchPopularMovieById(args.id)

        return binding.root
    }
}