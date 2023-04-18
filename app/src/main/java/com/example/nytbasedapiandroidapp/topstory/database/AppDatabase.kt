package com.example.nytbasedapiandroidapp.topstory.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.nytbasedapiandroidapp.topstory.models.ResultEntity
import com.example.nytbasedapiandroidapp.topstory.models.TopStoriesEntity

// db/AppDatabase.kt


@Database(entities = [TopStoriesEntity::class, ResultEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun topStoriesDao(): TopStoriesDao
}
