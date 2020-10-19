package my.test.movieexpert.domain.entity

import java.io.Serializable

abstract class Movie : Serializable {
    abstract val id: Int
    abstract val title: String
    abstract val overview: String
    abstract val voteAverage: Double
    abstract val releaseDate: String
    abstract val posterUrl: String?
}
