package com.example.nytbasedapiandroidapp.topstory.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromStringListToJson(value: List<String>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromJsonToStringList(value: String): List<String>? {
        val listType = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromMultimediaEntityListToJson(value: List<MultimediaEntity>?): String {
        return gson.toJson(value)
    }

    @TypeConverter
    fun fromJsonToMultimediaEntityList(value: String): List<MultimediaEntity>? {
        val listType = object : TypeToken<List<MultimediaEntity>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromResultEntityList(resultEntityList: List<ResultEntity>): String {
        return gson.toJson(resultEntityList)
    }

    @TypeConverter
    fun toResultEntityList(resultEntityListString: String): List<ResultEntity> {
        val type = object : TypeToken<List<ResultEntity>>() {}.type
        return gson.fromJson(resultEntityListString, type)
    }
}

