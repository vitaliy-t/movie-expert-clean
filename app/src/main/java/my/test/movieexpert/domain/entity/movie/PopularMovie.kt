package my.test.movieexpert.domain.entity.movie

data class PopularMovie(
    override val id: Int,
    override val title: String,
    override val overview: String,
    override val voteAverage: String,
    override val releaseDate: String,
    override val posterUrl: String?
) : Movie()