package com.estebanserrano.ktestfarmatodo.data.service.api

import com.estebanserrano.ktestfarmatodo.data.service.response.CharacterResponse
import com.estebanserrano.ktestfarmatodo.data.service.response.DataBaseResponse
import com.estebanserrano.ktestfarmatodo.data.service.response.MarvelBaseResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelApi {

    @GET("/v1/public/series/354/{search}")
    fun getCharacter(@Path("search") search: String): Single<MarvelBaseResponse<DataBaseResponse<CharacterResponse>>>

}