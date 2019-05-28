package com.estebanserrano.ktestfarmatodo.presentation.viewModel.main

sealed class MainViewState {
    class ShowResult(val result: String) : MainViewState()
    class ShowMultipleResult(val result: String) : MainViewState()
}