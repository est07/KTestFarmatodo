package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.CharacterResponse
import com.estebanserrano.ktestfarmatodo.data.service.response.ThumbnailResponse
import com.estebanserrano.ktestfarmatodo.domain.model.Thumbnail
import com.estebanserrano.ktestfarmatodo.domain.model.Character

class CharacterMapperService :
    BaseMapperService<CharacterResponse, Character> {

    override fun transform(characterResponse: CharacterResponse): Character
            = Character(
        characterResponse.name,
        characterResponse.description,
        transformToThumbnail(characterResponse.thumbnail)
    )

    override fun transformToResponse(type: Character): CharacterResponse
            = CharacterResponse(
        type.name,
        type.description,
        transformToThumbnailResponse(type.thumbnail)
    )

    fun transformToThumbnail(thumbnailResponse: ThumbnailResponse): Thumbnail
            = Thumbnail(
        thumbnailResponse.path,
        thumbnailResponse.extension
    )

    fun transformToThumbnailResponse(thumbnail: Thumbnail): ThumbnailResponse
            = ThumbnailResponse(
        thumbnail.path,
        thumbnail.extension
    )

    fun transform(charactersResponse: List<CharacterResponse>): List<Character>
            = charactersResponse.map { transform(it) }

}