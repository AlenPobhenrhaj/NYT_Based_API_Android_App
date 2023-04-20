package com.example.nytbasedapiandroidapp.topstory.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "multimedia")
data class MultimediaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val caption: String,
    val copyright: String,
    val format: String,
    val height: Int,
    val url: String,
)