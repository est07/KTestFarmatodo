package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.EventsResponse
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard

class EventsMapperService : BaseMapperService<EventsResponse, MarvelCard>{

    override fun transform(eventsResponse: EventsResponse): MarvelCard
            = MarvelCard(
            eventsResponse.name,
            eventsResponse.description,
            transformToThumbnail(eventsResponse.thumbnail)
    )

    override fun transformToResponse(type: MarvelCard): EventsResponse
            = EventsResponse(
            type.header,
            type.description,
            transformToThumbnailResponse(type.thumbnail)
    )

    fun transform(eventsResponse: List<EventsResponse>): List<MarvelCard>
            = eventsResponse.map { transform(it) }

}