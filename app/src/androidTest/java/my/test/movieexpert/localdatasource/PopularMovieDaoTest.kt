package my.test.movieexpert.localdatasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import my.test.movieexpert.data.localdatasource.dao.PopularMovieDao
import my.test.movieexpert.data.localdatasource.database.MainDatabase
import org.junit.After
import org.junit.Before
import org.junit.Rule
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
}