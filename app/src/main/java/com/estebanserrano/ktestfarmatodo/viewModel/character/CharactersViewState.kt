package com.estebanserrano.ktestfarmatodo.viewModel.character

import com.estebanserrano.ktestfarmatodo.domain.model.Character

sealed class CharactersViewState {
    class ShowItems(val items: List<Character>) : CharactersViewState()
    class ShowMessage(val message: String) : CharactersViewState()

}