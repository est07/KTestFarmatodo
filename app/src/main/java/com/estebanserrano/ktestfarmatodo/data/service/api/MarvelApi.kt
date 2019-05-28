package com.estebanserrano.ktestfarmatodo.data.service.api

import com.estebanserrano.ktestfarmatodo.data.service.response.*
import io.reactivex.Single
import retrofit2.http.GET

interface MarvelApi {

    @GET("/v1/public/characters")
    fun getCharacter(): Single<MarvelBaseResponse<DataBaseResponse<CharactersResponse>>>

    @GET("/v1/public/comics")
    fun getComics(): Single<MarvelBaseResponse<DataBaseResponse<ComicResponse>>>

    @GET("/v1/public/creators")
    fun getCreators(): Single<MarvelBaseResponse<DataBaseResponse<CreatorResponse>>>

    @GET("/v1/public/events/227/characters")
    fun getEvents(): Single<MarvelBaseResponse<DataBaseResponse<EventsResponse>>>

    @GET("/v1/public/series/354/characters")
    fun getSeries(): Single<MarvelBaseResponse<DataBaseResponse<SeriesResponse>>>

    @GET("/v1/public/stories")
    fun getStories(): Single<MarvelBaseResponse<DataBaseResponse<StoriesResponse>>>



}