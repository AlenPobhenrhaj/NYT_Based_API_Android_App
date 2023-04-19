package com.example.nytbasedapiandroidapp.topstory.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.nytbasedapiandroidapp.topstory.models.Converters
import com.example.nytbasedapiandroidapp.topstory.models.TopStoriesEntity

// db/TopStoriesDatabase.kt


@Database(entities = [TopStoriesEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class) // Add this line
abstract class TopStoriesDatabase : RoomDatabase() {
    abstract fun topStoriesDao(): TopStoriesDao

    companion object {
        @Volatile
        private var INSTANCE: TopStoriesDatabase? = null

        fun getDatabase(context: Context): TopStoriesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TopStoriesDatabase::class.java,
                    "top_stories_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
