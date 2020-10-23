package my.test.movieexpert.domain.entity.movie

data class LatestMovie(
    override val id: Int,
    override val title: String,
    override val overview: String,
    override val voteAverage: String,
    override val releaseDate: String,
    override val posterUrl: String?,

    val adult: Boolean,
    val originalLang: String,
    val originalTitle: String,
    val budget: String,
    val revenue: String,
    val runtime: String,
    val popularity: String,
    val status: String,
) : Movie()