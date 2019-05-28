package com.estebanserrano.ktestfarmatodo.data.mapper

import com.estebanserrano.ktestfarmatodo.data.service.response.ThumbnailResponse
import com.estebanserrano.ktestfarmatodo.domain.model.Thumbnail

/**
 * Interface for model mappers. It provides helper methods that facilitate
 * retrieving of models from outer data source layers
 *
 * @param <T> the cached model input type
 * @param <T> the remote model input type
 * @param <V> the model return type
 */

interface BaseMapperService <R, D> {

    fun transform(type: R): D

    fun transformToResponse(type: D): R


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
}