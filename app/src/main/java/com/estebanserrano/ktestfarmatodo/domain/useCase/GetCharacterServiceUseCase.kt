package com.estebanserrano.ktestfarmatodo.domain.useCase

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.CharacterServices
import io.reactivex.Single

open class GetCharacterServiceUseCase constructor(private val characterServiceImp: CharacterServices) :BaseServiceUseCase {

    override fun invoke(): Single<List<MarvelCard>> = characterServiceImp.getCharacters()
}