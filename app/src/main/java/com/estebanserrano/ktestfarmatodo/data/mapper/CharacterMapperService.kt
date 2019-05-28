package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.CharactersResponse
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard

class CharacterMapperService : BaseMapperService<CharactersResponse, MarvelCard> {

    override fun transform(charactersResponse: CharactersResponse): MarvelCard
            = MarvelCard(
            charactersResponse.name,
            charactersResponse.description,
            transformToThumbnail(charactersResponse.thumbnail)
    )

    override fun transformToResponse(type: MarvelCard): CharactersResponse
            = CharactersResponse(
            type.header,
            type.description,
            transformToThumbnailResponse(type.thumbnail)
    )

    fun transform(charactersResponse: List<CharactersResponse>): List<MarvelCard>
            = charactersResponse.map { transform(it) }

}