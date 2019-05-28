package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.CreatorResponse
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard

class CreatorMapperService : BaseMapperService<CreatorResponse, MarvelCard> {


    override fun transform(type: CreatorResponse): MarvelCard = MarvelCard(
        type.fullName,
        "FirstName : ${type.firstName} Modified: ${type.modified}",
        transformToThumbnail(type.thumbnail))

    override fun transformToResponse(type: MarvelCard): CreatorResponse = CreatorResponse(
        type.header,
        type.description,
        type.description,
        transformToThumbnailResponse(type.thumbnail))
}