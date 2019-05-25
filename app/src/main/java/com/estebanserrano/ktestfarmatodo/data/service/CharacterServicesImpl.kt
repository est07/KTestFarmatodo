package com.estebanserrano.ktestfarmatodo.data.service

import com.estebanserrano.ktestfarmatodo.data.mapper.CharacterMapperService
import com.estebanserrano.ktestfarmatodo.domain.model.Character
import com.estebanserrano.ktestfarmatodo.domain.service.CharacterServices
import io.reactivex.Single

class CharacterServicesImpl(private val api: MarvelResquestGenerator = MarvelResquestGenerator(), private val mapper: CharacterMapperService = CharacterMapperService()) : CharacterServices {
    override fun getCharacters(search: String): Single<List<Character>> {
        return api.makeMarvelService().getCharacter(search).map { response ->
            response.data!!.result.map { characterResponse -> mapper.transform(characterResponse) }
        }
    }
}