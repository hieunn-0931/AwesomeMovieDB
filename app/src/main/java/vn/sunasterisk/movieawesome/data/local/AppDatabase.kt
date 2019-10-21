package vn.sunasterisk.movieawesome.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.sunasterisk.movieawesome.data.entity.Movie

@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}
