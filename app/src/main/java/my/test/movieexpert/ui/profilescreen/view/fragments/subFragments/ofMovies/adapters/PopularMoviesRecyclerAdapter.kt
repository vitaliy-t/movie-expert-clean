package my.test.movieexpert.ui.profilescreen.view.fragments.subFragments.ofMovies.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import my.test.movieexpert.databinding.FragmentPopularRecyclerViewLayoutBinding
import my.test.movieexpert.domain.entity.PopularMovie
import my.test.movieexpert.ui.profilescreen.view.fragments.subFragments.ofMovies.MoviesPopularFragmentDirections

class PopularMoviesRecyclerAdapter : ListAdapter<PopularMovie, PopularMovieHolder>(
    PopularMovieDiffer
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieHolder {
        return PopularMovieHolder(FragmentPopularRecyclerViewLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PopularMovieHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class PopularMovieHolder(private val binding: FragmentPopularRecyclerViewLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
    init {
        binding.setClickListener { view ->
            binding.movie?.let { movie ->
                navigateToMovie(movie, view)
            }
        }
    }

    private fun navigateToMovie(movie: PopularMovie, view: View) {
        val direction = MoviesPopularFragmentDirections.actionPopularFragmentToMovieDetailsFragment(
            movie = movie
        )
        view.findNavController().navigate(direction)
    }

    fun bind(movie: PopularMovie) {
        binding.movie = movie
        binding.executePendingBindings()
    }
}

private object PopularMovieDiffer : DiffUtil.ItemCallback<PopularMovie>() {
    override fun areItemsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: PopularMovie, newItem: PopularMovie): Boolean {
        return oldItem == newItem
    }
}