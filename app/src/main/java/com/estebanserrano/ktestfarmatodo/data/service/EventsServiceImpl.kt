package com.estebanserrano.ktestfarmatodo.data.service

import com.estebanserrano.ktestfarmatodo.data.mapper.EventsMapperService
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.EventsServices
import io.reactivex.Single

class EventsServiceImpl (private val  api: MarvelResquestGenerator= MarvelResquestGenerator(),
                         private val mapper: EventsMapperService = EventsMapperService()
) :EventsServices {
    override fun getEvents(): Single<List<MarvelCard>>{
        return api.makeMarvelService().getEvents().map { response ->
            response.data!!.result.map { eventsResponse -> mapper.transform(eventsResponse) }
        }
    }
}