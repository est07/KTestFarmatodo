package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.SeriesServices
import io.reactivex.Single

class GetSeriesServiceUseCase constructor(val service: SeriesServices): BaseServiceUseCase {
    override fun invoke(): Single<List<MarvelCard>> = service.getSeries()
}
