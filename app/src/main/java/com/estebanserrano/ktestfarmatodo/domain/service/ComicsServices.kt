package com.estebanserrano.ktestfarmatodo.domain.service

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import io.reactivex.Single

interface ComicsServices {
    fun getComics(): Single<List<MarvelCard>>
}