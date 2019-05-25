package com.estebanserrano.ktestfarmatodo.domain.service

import com.estebanserrano.ktestfarmatodo.domain.model.Character
import io.reactivex.Single

interface CharacterServices {
    fun getCharacters(search: String): Single<List<Character>>
}