package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.ComicResponse
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard

class ComicMapperService : BaseMapperService<ComicResponse, MarvelCard> {

    override fun transform(response: ComicResponse): MarvelCard = MarvelCard(
        response.title,
        response.variantDescription,
        transformToThumbnail(response.thumbnail))

    override fun transformToResponse(type: MarvelCard): ComicResponse = ComicResponse(
        type.header,
        type.description,
        transformToThumbnailResponse(type.thumbnail))

}