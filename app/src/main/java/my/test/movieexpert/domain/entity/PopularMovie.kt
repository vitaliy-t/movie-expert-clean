package my.test.movieexpert.domain.entity

data class PopularMovie(
    override val id: Int,
    override val title: String,
    override val overview: String,
    override val vote_average: Double,
    override val release_date: String,
    override val poster_path: String?
) : Movie()