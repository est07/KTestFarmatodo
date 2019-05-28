package com.estebanserrano.ktestfarmatodo.data.service

import com.estebanserrano.ktestfarmatodo.data.mapper.CharacterMapperService
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.service.CharacterServices
import io.reactivex.Single

class CharactersServicesImpl(private val api: MarvelResquestGenerator = MarvelResquestGenerator(),
                             private val mapper: CharacterMapperService = CharacterMapperService()) : CharacterServices {
    override fun getCharacters(): Single<List<MarvelCard>> {
        return api.makeMarvelService().getCharacter().map { response ->
            response.data!!.result.map { characterResponse -> mapper.transform(characterResponse) }
        }
    }
}