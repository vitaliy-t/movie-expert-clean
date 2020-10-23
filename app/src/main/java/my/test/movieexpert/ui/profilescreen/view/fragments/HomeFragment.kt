package my.test.movieexpert.ui.profilescreen.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import my.test.movieexpert.databinding.FragmentHomeBinding
import my.test.movieexpert.domain.state.ViewState
import my.test.movieexpert.ui.profilescreen.viewmodel.HomeViewModel

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = homeViewModel

        homeViewModel.latestMovieState.observe(viewLifecycleOwner, { state ->
            when (state) {
                !is ViewState.Loading -> binding.homeSwipeRefresh.isRefreshing = false
            }
        })

        binding.homeSwipeRefresh.setOnRefreshListener {
            binding.homeSwipeRefresh.isRefreshing = true
            homeViewModel.getLatestMovie()
        }

        return binding.root
    }
}