package com.estebanserrano.ktestfarmatodo.viewModel.detail

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.estebanserrano.ktestfarmatodo.viewModel.ScreenState

class DetailViewModel (private val detailInteractor: DetailInteractor): ViewModel(),
    DetailInteractor.onGetDetailCharacterData {


    private var detailViewState: MutableLiveData<ScreenState<DetailViewState>> = MutableLiveData()
    val detailState: LiveData<ScreenState<DetailViewState>>
        get() = detailViewState

    fun onGetDataItems(name: String, description: String, image: String) {

        detailViewState.value = ScreenState.Loading
        detailInteractor.detailCharacterData(name,description,image, this)
    }

    override fun onGetDataItemsError() {
        detailViewState.value = ScreenState.Render(DetailViewState.CharacterDataError)
    }

    override fun onGetDataItemsSucces() {
        detailViewState.value = ScreenState.Render(DetailViewState.Success)
    }
}

class DetailViewModelFactory(private val detailInteractor: DetailInteractor) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailViewModel(detailInteractor) as T
    }
}