package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.SeriesResponse
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard

class SeriesMapperService: BaseMapperService<SeriesResponse, MarvelCard> {

    override fun transform(seriesResponse: SeriesResponse): MarvelCard
            = MarvelCard(
            seriesResponse.name,
            seriesResponse.description,
            transformToThumbnail(seriesResponse.thumbnail)
    )

    override fun transformToResponse(type: MarvelCard): SeriesResponse
            = SeriesResponse(
            type.header,
            type.description,
            transformToThumbnailResponse(type.thumbnail)
    )

    fun transform(seriesResponse: List<SeriesResponse>): List<MarvelCard>
            = seriesResponse.map { transform(it) }
}