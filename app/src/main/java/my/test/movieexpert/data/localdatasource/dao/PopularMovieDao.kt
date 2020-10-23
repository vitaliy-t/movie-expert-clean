package my.test.movieexpert.data.localdatasource.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import my.test.movieexpert.data.localdatasource.entity.PopularMovieRoomEntity


@Dao
interface PopularMovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(listOfMovies: List<PopularMovieRoomEntity>)

    @Query("DELETE FROM PopularMovieRoomEntity")
    suspend fun clearTable()

    @Query("SELECT * FROM PopularMovieRoomEntity")
    suspend fun getMovies(): List<PopularMovieRoomEntity>?

    @Query("SELECT * FROM PopularMovieRoomEntity WHERE id = :id")
    suspend fun getMovieById(id: Int): PopularMovieRoomEntity
}