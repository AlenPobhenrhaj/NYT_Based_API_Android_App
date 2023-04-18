package com.example.nytbasedapiandroidapp.topstory.models

// db/TopStoriesEntity.kt
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "top_stories")
data class TopStoriesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val copyright: String,
    val last_updated: String,
    val num_results: Int,
    val section: String,
    val status: String,
    val result: List<ResultEntity>
)
