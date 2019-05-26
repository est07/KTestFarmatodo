package com.estebanserrano.ktestfarmatodo.presentation.viewModel

sealed class ScreenState<out T> {
    object Loading : ScreenState<Nothing>()
    class Render<T>(val renderState: T) : ScreenState<T>()
}