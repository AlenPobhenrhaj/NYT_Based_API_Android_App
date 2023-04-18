package com.example.nytbasedapiandroidapp.topstory.mapper

import com.example.nytbasedapiandroidapp.topstory.models.TopStories
import com.example.nytbasedapiandroidapp.topstory.models.TopStoriesEntity

// TopStoriesMapper.kt
object TopStoriesMapper {
    fun mapToEntity(topStories: TopStories): TopStoriesEntity {
        // Add your conversion logic here.
        // For example:
        return TopStoriesEntity(
            id = 0, // or generate an ID if required
            last_updated = topStories.last_updated,
            num_results = topStories.num_results,
            results = topStories.results,
        )
    }

    fun mapToModel(topStoriesEntity: TopStoriesEntity): TopStories {
        // Add your conversion logic here.
        // For example:
        return TopStories(
            last_updated = topStoriesEntity.last_updated,
            num_results = topStoriesEntity.num_results,
            results = topStoriesEntity.results,
            section = "", // If your TopStoriesEntity has a section field, use it here
            status = "OK" // If your TopStoriesEntity has a status field, use it here
        )
    }
}

