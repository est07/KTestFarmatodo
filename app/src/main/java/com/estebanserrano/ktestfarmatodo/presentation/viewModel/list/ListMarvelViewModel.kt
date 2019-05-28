package com.estebanserrano.ktestfarmatodo.presentation.viewModel.list

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.useCase.BaseServiceUseCase
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.ScreenState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ListMarvelViewModel(private val useCase: BaseServiceUseCase) : ViewModel() {

    private var listMarvelViewState: MutableLiveData<ScreenState<ListMarvelViewState>> = MutableLiveData()
    private var subscriptions = CompositeDisposable()

    val listMarvelState: LiveData<ScreenState<ListMarvelViewState>>
        get()= listMarvelViewState

    fun getCharacters(): LiveData<ScreenState<ListMarvelViewState>> {
        listMarvelViewState.value = ScreenState.Loading
        val subscription = useCase.invoke()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                onItemsLoaded(characters)
            }, { error ->
                showError(error.message.toString())
            })
        subscriptions.add(subscription)

        return listMarvelViewState
    }

    private fun onItemsLoaded(items: List<MarvelCard>) {
        listMarvelViewState.value = ScreenState.Render(ListMarvelViewState.ShowItems(items))
    }

    private fun showError(message: String) {
        listMarvelViewState.value = ScreenState.Render(ListMarvelViewState.ShowMessage(message))
    }

    override fun onCleared() {
        subscriptions.dispose()
        super.onCleared()
    }
}

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val useCase: BaseServiceUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListMarvelViewModel(useCase) as T
    }
}