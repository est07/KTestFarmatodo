package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import io.reactivex.Single

interface BaseServiceUseCase {
    fun invoke(): Single<List<MarvelCard>>
}