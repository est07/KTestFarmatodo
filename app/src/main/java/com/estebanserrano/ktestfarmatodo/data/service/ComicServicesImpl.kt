package com.estebanserrano.ktestfarmatodo.data.service

import com.estebanserrano.ktestfarmatodo.data.mapper.ComicMapperService
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.ComicsServices
import io.reactivex.Single

class ComicSerivicesImpl constructor(private val api: MarvelResquestGenerator = MarvelResquestGenerator(),
                                     private val mapper: ComicMapperService = ComicMapperService())
    : ComicsServices {
    override fun getComics(): Single<List<MarvelCard>> {
        return api.makeMarvelService().getComics().map { response ->
            response.data!!.result.map { comicsResponse -> mapper.transform(comicsResponse) }
        }
    }
}