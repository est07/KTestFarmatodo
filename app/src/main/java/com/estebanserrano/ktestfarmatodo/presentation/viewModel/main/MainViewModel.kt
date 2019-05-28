package com.estebanserrano.ktestfarmatodo.presentation.viewModel.main

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.ScreenState

class MainViewModel(private val mainInteractor: MainViewInteractor) : ViewModel(),
    MainViewInteractor.OnMainFinishedListener {

    private val _mainState: MutableLiveData<ScreenState<MainViewState>> = MutableLiveData()

    val mainState: LiveData<ScreenState<MainViewState>>
        get() = _mainState

    fun onEqualsClicked(number: String) {
        _mainState.value = ScreenState.Loading
        mainInteractor.doOperation(number, this)
    }

    override fun onOperationError(error: String) {
        _mainState.value = ScreenState.Render(MainViewState.ShowResult(error))
    }
    override fun onSuccess(number: String) {
        _mainState.value = ScreenState.Render(MainViewState.ShowResult(number))
    }

    fun onfindMultiple(result: String) {
        mainInteractor.findTheMultiple(result, this)
    }

    override fun onfindTheMultiple(number: String) {
        _mainState.value = ScreenState.Render(MainViewState.ShowMultipleResult(number))
    }

}

class MainViewModelFactory(private val mainInteractor: MainViewInteractor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(mainInteractor) as T
    }
}