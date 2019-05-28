package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.CreatorsService
import io.reactivex.Single

class GetCreatorsServiceUseCase constructor(val service: CreatorsService) : BaseServiceUseCase {

    override fun invoke(): Single<List<MarvelCard>> = service.getCreators()

}