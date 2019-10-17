package vn.sunasterisk.movieawesome.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.Query
import vn.sunasterisk.movieawesome.data.entity.Movie

@Dao
interface MovieDao {
//    @Query("SELECT * FROM movie")
//    suspend fun getMovieList(): List<Movie>?
//
//    @Insert(onConflict = IGNORE)
//    suspend fun insert(movie: Movie)
//
//    @Delete
//    suspend fun deleteMovie(movie: Movie)
//
//    @Query("DELETE FROM movie WHERE id = :id")
//    suspend fun deleteMovie(id: String)
//
//    @Query("DELETE FROM movie")
//    suspend fun deleteAll()
}
