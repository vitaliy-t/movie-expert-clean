package my.test.movieexpert.domain.entity

data class PopularMovie(
    override val id: Int,
    override val title: String,
    override val overview: String,
    override val voteAverage: Double,
    override val releaseDate: String,
    override val posterUrl: String?
) : Movie()