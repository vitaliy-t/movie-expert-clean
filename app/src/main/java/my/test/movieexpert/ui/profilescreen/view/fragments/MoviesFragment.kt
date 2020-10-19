package my.test.movieexpert.ui.profilescreen.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import my.test.movieexpert.R
import my.test.movieexpert.databinding.FragmentMoviesBinding
import my.test.movieexpert.ui.profilescreen.viewModel.MoviesViewModel

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private val moviesViewModel: MoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMoviesBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = moviesViewModel

        binding.popularMovies.setOnClickListener {
            this.findNavController().navigate(R.id.action_moviesFragment_to_popularFragment)
        }

        return binding.root
    }
}