package com.estebanserrano.ktestfarmatodo.viewModel.main

sealed class MainViewState {
    class ShowResult(val result: String) : MainViewState()
}