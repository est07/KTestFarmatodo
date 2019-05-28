package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.StoriesResponse
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard

class StoriesMapperService: BaseMapperService<StoriesResponse, MarvelCard>{

    override fun transform(response: StoriesResponse): MarvelCard = MarvelCard(
        response.type,
        response.title,
        transformToThumbnail(response.thumbnail))

    override fun transformToResponse(type: MarvelCard): StoriesResponse = StoriesResponse(
        type.header,
        type.description,
        transformToThumbnailResponse(type.thumbnail))
}