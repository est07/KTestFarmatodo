package com.estebanserrano.ktestfarmatodo.presentation.viewModel.list

import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard

sealed class ListMarvelViewState {
    class ShowItems(val items: List<MarvelCard>) : ListMarvelViewState()
    class ShowMessage(val message: String) : ListMarvelViewState()

}