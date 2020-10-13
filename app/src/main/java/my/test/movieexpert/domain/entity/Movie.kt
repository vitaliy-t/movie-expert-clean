package my.test.movieexpert.domain.entity

import java.io.Serializable

abstract class Movie : Serializable {
    abstract val id: Int
    abstract val title: String
    abstract val overview: String
    abstract val vote_average: Double
    abstract val release_date: String
    abstract val poster_path: String?
}
