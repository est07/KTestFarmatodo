package com.estebanserrano.ktestfarmatodo.data.service

import com.estebanserrano.ktestfarmatodo.data.mapper.CreatorMapperService
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.CreatorsService
import io.reactivex.Single

class CreatorsServiceImpl constructor(private val api: MarvelResquestGenerator = MarvelResquestGenerator(),
                                      private val mapper: CreatorMapperService = CreatorMapperService())
    : CreatorsService {
    override fun getCreators(): Single<List<MarvelCard>> {
        return api.makeMarvelService().getCreators().map { response ->
            response.data!!.result.map { comicsResponse -> mapper.transform(comicsResponse) }
        }
    }
}