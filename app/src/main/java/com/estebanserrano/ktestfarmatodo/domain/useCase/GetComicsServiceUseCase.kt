package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.ComicsServices
import io.reactivex.Single

class GetComicsServiceUseCase constructor(private val comicsServices: ComicsServices) :BaseServiceUseCase {

    override fun invoke(): Single<List<MarvelCard>> = comicsServices.getComics()

}