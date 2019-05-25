package com.estebanserrano.ktestfarmatodo.domain.repository

import com.estebanserrano.ktestfarmatodo.domain.model.Character

interface CharacterRepository {
    fun getAll() : List<Character>
    fun save(c: List<Character>)
}