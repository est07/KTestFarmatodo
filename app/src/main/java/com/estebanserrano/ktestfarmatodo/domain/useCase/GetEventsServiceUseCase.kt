package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.CreatorsService
import com.estebanserrano.ktestfarmatodo.domain.service.EventsServices
import io.reactivex.Single

class GetEventsServiceUseCase constructor(val service: EventsServices): BaseServiceUseCase {

    override fun invoke(): Single<List<MarvelCard>> = service.getEvents()
}
