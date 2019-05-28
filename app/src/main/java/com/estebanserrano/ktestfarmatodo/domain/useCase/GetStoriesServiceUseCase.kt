package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.StoriesServices
import io.reactivex.Single

class GetStoriesServiceUseCase constructor(val service: StoriesServices): BaseServiceUseCase{
    override fun invoke(): Single<List<MarvelCard>> = service.getStories()
}
