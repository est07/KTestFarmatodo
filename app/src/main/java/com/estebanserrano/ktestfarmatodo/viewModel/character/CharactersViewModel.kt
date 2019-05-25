package com.estebanserrano.ktestfarmatodo.viewModel.character

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.estebanserrano.ktestfarmatodo.domain.useCase.GetCharacterServiceUseCase
import com.estebanserrano.ktestfarmatodo.viewModel.ScreenState
import com.estebanserrano.ktestfarmatodo.domain.model.Character
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharactersViewModel(private val useCase: GetCharacterServiceUseCase) : ViewModel() {


    private var charactersViewState: MutableLiveData<ScreenState<CharactersViewState>> = MutableLiveData()
    private var subscriptions = CompositeDisposable()


    val mainState: LiveData<ScreenState<CharactersViewState>>
        get()= charactersViewState

    fun getCharacters(search: String): LiveData<ScreenState<CharactersViewState>> {
        charactersViewState.value = ScreenState.Loading
        val subscription = useCase.invoke(search)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ characters ->
                onItemsLoaded(characters)
            }, { error ->
                showError(error.message.toString())
            })
        subscriptions.add(subscription)

        return charactersViewState
    }

    private fun onItemsLoaded(items: List<Character>) {
        charactersViewState.value = ScreenState.Render(CharactersViewState.ShowItems(items))
    }

    private fun showError(message: String) {
        charactersViewState.value = ScreenState.Render(CharactersViewState.ShowMessage(message))
    }

    override fun onCleared() {
        subscriptions.dispose()
        super.onCleared()
    }
}

@Suppress("UNCHECKED_CAST")
class CharactersViewModelFactory(private val useCase: GetCharacterServiceUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CharactersViewModel(useCase) as T
    }
}