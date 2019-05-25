package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.Character
import com.estebanserrano.ktestfarmatodo.domain.service.CharacterServices
import io.reactivex.Single

open class GetCharacterServiceUseCase constructor(private val characterServiceImp: CharacterServices) {

    open operator fun invoke(search: String): Single<List<Character>> = characterServiceImp.getCharacters(search)
}