package com.estebanserrano.ktestfarmatodo.presentation.viewModel.List

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.estebanserrano.ktestfarmatodo.domain.useCase.GetCharacterServiceUseCase
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.ScreenState
import com.estebanserrano.ktestfarmatodo.domain.model.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharactersViewModel(private val useCase: GetCharacterServiceUseCase) : ViewModel() {


    private var listViewState: MutableLiveData<ScreenState<ListViewState>> = MutableLiveData()
    private var subscriptions = CompositeDisposable()


    val mainState: LiveData<ScreenState<ListViewState>>
        get()= listViewState

    fun getCharacters(search: String): LiveData<ScreenState<ListViewState>> {
        listViewState.value = ScreenState.Loading
        val subscription = useCase.invoke(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                onItemsLoaded(characters)
            }, { error ->
                showError(error.message.toString())
            })
        subscriptions.add(subscription)

        return listViewState
    }

    private fun onItemsLoaded(items: List<Character>) {
        listViewState.value = ScreenState.Render(ListViewState.ShowItems(items))
    }

    private fun showError(message: String) {
        listViewState.value = ScreenState.Render(ListViewState.ShowMessage(message))
    }

    override fun onCleared() {
        subscriptions.dispose()
        super.onCleared()
    }
}

@Suppress("UNCHECKED_CAST")
class ListViewModelFactory(private val useCase: GetCharacterServiceUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(useCase) as T
    }
}