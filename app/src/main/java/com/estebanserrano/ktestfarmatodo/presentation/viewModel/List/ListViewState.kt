package com.estebanserrano.ktestfarmatodo.presentation.viewModel.List

import com.estebanserrano.ktestfarmatodo.domain.model.Character

sealed class ListViewState {
    class ShowItems(val items: List<Character>) : ListViewState()
    class ShowMessage(val message: String) : ListViewState()

}