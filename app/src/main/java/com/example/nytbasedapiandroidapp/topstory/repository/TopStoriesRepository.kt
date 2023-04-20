package com.example.nytbasedapiandroidapp.topstory.repository

//TopStoriesRepository.kt
import com.example.nytbasedapiandroidapp.topstory.data.NYTApiService
import com.example.nytbasedapiandroidapp.topstory.database.TopStoriesDao
import com.example.nytbasedapiandroidapp.topstory.mapper.TopStoriesMapper
import com.example.nytbasedapiandroidapp.topstory.models.TopStories

class TopStoriesRepository(private val apiService: NYTApiService, private val topStoriesDao: TopStoriesDao) {

    suspend fun getTopStories(): TopStories? {
        // Fetch data from the API
        val response = apiService.getTopStories("VnAPYRcntWaevUsIClh5lsmM5AbTkDxj")
        if (response.isSuccessful) {
            val topStories = response.body()
            if (topStories != null) {
                // Save data to the local cache
                val topStoriesEntity = TopStoriesMapper.mapToEntity(topStories)
                topStoriesDao.insertTopStories(topStoriesEntity)
                return topStories
            }
        }
        // Fetch data from the local cache if the API call fails
        val topStoriesEntity = topStoriesDao.getTopStories()
        return topStoriesEntity?.let { TopStoriesMapper.mapToModel(it) }
    }
}

