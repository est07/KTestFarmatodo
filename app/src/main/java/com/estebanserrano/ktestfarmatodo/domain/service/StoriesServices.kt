package com.estebanserrano.ktestfarmatodo.domain.service

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import io.reactivex.Single

interface StoriesServices {
    fun getStories(): Single<List<MarvelCard>>
}