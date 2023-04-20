package com.example.nytbasedapiandroidapp.topstory.database

//TopStoriesDao.kt
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.nytbasedapiandroidapp.topstory.models.TopStoriesEntity

@Dao
interface TopStoriesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTopStories(topStories: TopStoriesEntity)

    @Query("SELECT * FROM top_stories LIMIT 1")
    suspend fun getTopStories(): TopStoriesEntity?
}
