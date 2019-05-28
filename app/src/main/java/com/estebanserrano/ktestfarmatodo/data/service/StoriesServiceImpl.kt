package com.estebanserrano.ktestfarmatodo.data.service

import com.estebanserrano.ktestfarmatodo.data.mapper.StoriesMapperService
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.StoriesServices
import io.reactivex.Single

class StoriesServiceImpl (private val api:MarvelResquestGenerator= MarvelResquestGenerator(),
                          private val mapper: StoriesMapperService = StoriesMapperService()
): StoriesServices {
    override fun getStories(): Single<List<MarvelCard>> {
        return api.makeMarvelService().getStories().map { response ->
            response.data!!.result.map { storiesResponse -> mapper.transform(storiesResponse) }
        }
    }
}