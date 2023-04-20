package com.example.nytbasedapiandroidapp.topstory.mapper

import com.example.nytbasedapiandroidapp.topstory.models.*

// TopStoriesMapper.kt
object TopStoriesMapper {

    fun mapMultimediaToEntity(multimedia: Multimedia): MultimediaEntity {
        return MultimediaEntity(
            // Map properties from Multimedia to MultimediaEntity
            caption = multimedia.caption,
            copyright = multimedia.copyright,
            format = multimedia.format,
            height = multimedia.height,
            url = multimedia.url,
        )
    }

    private fun mapMultimediaEntityToModel(multimediaEntity: MultimediaEntity): Multimedia {
        return Multimedia(
            // Map properties from MultimediaEntity to Multimedia
            caption = multimediaEntity.caption,
            copyright = multimediaEntity.copyright,
            format = multimediaEntity.format,
            height = multimediaEntity.height,
            url = multimediaEntity.url,
        )
    }

    private fun mapResultToEntity(result: Result): ResultEntity {
        return ResultEntity(
            // Map properties from Result to ResultEntity
            `abstract` = result.`abstract`,
            byline = result.byline,
            created_date = result.created_date,
            des_facet = result.des_facet,
            geo_facet = result.geo_facet,
            item_type = result.item_type,
            kicker = result.kicker,
            material_type_facet = result.material_type_facet,
            multimedia = result.multimedia.map(::mapMultimediaToEntity),
            org_facet = result.org_facet,
            per_facet = result.per_facet,
            published_date = result.published_date,
            section = result.section,
            short_url = result.short_url,
            subsection = result.subsection,
            title = result.title,
            updated_date = result.updated_date,
            uri = result.uri,
            url = result.url
        )
    }

    private fun mapResultEntityToModel(resultEntity: ResultEntity): Result {
        return Result(
            // Map properties from ResultEntity to Result
            `abstract` = resultEntity.`abstract`,
            byline = resultEntity.byline,
            created_date = resultEntity.created_date,
            des_facet = resultEntity.des_facet,
            geo_facet = resultEntity.geo_facet,
            item_type = resultEntity.item_type,
            kicker = resultEntity.kicker,
            material_type_facet = resultEntity.material_type_facet,
            multimedia = resultEntity.multimedia.map(::mapMultimediaEntityToModel),
            org_facet = resultEntity.org_facet,
            per_facet = resultEntity.per_facet,
            published_date = resultEntity.published_date,
            section = resultEntity.section,
            short_url = resultEntity.short_url,
            subsection = resultEntity.subsection,
            title = resultEntity.title,
            updated_date = resultEntity.updated_date,
            uri = resultEntity.uri,
            url = resultEntity.url
        )
    }

    fun mapToEntity(topStories: TopStories): TopStoriesEntity {
        // Add your conversion logic here.
        // For example:
        return TopStoriesEntity(
            id = 0, // or generate an ID if required
            last_updated = topStories.last_updated,
            num_results = topStories.num_results,
            result = topStories.results.map(::mapResultToEntity),
            copyright = topStories.copyright,
            section = topStories.section,
            status = topStories.status,
        )
    }

    fun mapToModel(topStoriesEntity: TopStoriesEntity): TopStories {
        // Add your conversion logic here.
        // For example:
        return TopStories(
            last_updated = topStoriesEntity.last_updated,
            num_results = topStoriesEntity.num_results,
            results = topStoriesEntity.result.map(::mapResultEntityToModel),
            section = topStoriesEntity.section,
            status = topStoriesEntity.status,
            copyright = topStoriesEntity.copyright
        )
    }
}

