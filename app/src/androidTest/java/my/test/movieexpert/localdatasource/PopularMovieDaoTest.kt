package my.test.movieexpert.localdatasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import my.test.movieexpert.data.localdatasource.dao.PopularMovieDao
import my.test.movieexpert.data.localdatasource.database.MainDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class PopularMovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: MainDatabase
    private lateinit var dao: PopularMovieDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MainDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.popularMovieDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun addMovies() = runBlockingTest {
        val movie1 = PopularMovie(
            13215,
            "Shawshank Redemption",
            "...",
            "19-09-2000",
            "/shawshank_redemption_poster.jpg",
            "10",
        )

        val movie2 = PopularMovie(
            13215,
            "Creed",
            "...",
            "19-09-2000",
            "/creed_poster.jpg",
            "10",
        )

        val movie3 = PopularMovie(
            13215,
            "Godfather",
            "...",
            "19-09-2000",
            "/godfather_poster.jpg",
            "10",
        )

        val listOfMovies = listOf(movie1, movie2, movie3)

        dao.addMovies(listOfMovies)
        val roomResponse = dao.getMovies()

        assertThat(roomResponse).isNotEmpty()
    }

    @Test
    fun deleteMovies() = runBlockingTest {
        val movie1 = PopularMovie(
            13215,
            "Shawshank Redemption",
            "...",
            "19-09-2000",
            "/shawshank_redemption_poster.jpg",
            "10",
        )

        val movie2 = PopularMovie(
            13215,
            "Creed",
            "...",
            "19-09-2000",
            "/creed_poster.jpg",
            "10",
        )

        val movie3 = PopularMovie(
            13215,
            "Godfather",
            "...",
            "19-09-2000",
            "/godfather_poster.jpg",
            "10",
        )

        val listOfMovies = listOf(movie1, movie2, movie3)

        dao.addMovies(listOfMovies)
        dao.clearTable()

        val roomResponse = dao.getMovies()
        assertThat(roomResponse).isEmpty()
    }


    @Test
    fun getMovies() = runBlockingTest {
        val movie1 = PopularMovie(
            13215,
            "Shawshank Redemption",
            "...",
            "19-09-2000",
            "/shawshank_redemption_poster.jpg",
            "10",
        )

        val movie2 = PopularMovie(
            13215,
            "Creed",
            "...",
            "19-09-2000",
            "/creed_poster.jpg",
            "10",
        )

        val movie3 = PopularMovie(
            13215,
            "Godfather",
            "...",
            "19-09-2000",
            "/godfather_poster.jpg",
            "10",
        )

        val listOfMovies = listOf(movie1, movie2, movie3)

        dao.addMovies(listOfMovies)

        val roomResponse = dao.getMovies()
        assertThat(roomResponse).isEqualTo(listOfMovies)
    }
}