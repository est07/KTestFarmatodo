package com.estebanserrano.ktestfarmatodo.data.service

import com.estebanserrano.ktestfarmatodo.data.mapper.SeriesMapperService
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.SeriesServices
import io.reactivex.Single

class SeriesServicesImpl (private val api:MarvelResquestGenerator= MarvelResquestGenerator(),
                          private val mapper: SeriesMapperService = SeriesMapperService()
): SeriesServices {
    override fun getSeries(): Single<List<MarvelCard>> {
        return api.makeMarvelService().getSeries().map { response ->
            response.data!!.result.map { seriesResponse -> mapper.transform(seriesResponse) }
        }
    }
}