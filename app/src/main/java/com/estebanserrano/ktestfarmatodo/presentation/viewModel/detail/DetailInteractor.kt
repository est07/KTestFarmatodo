package com.estebanserrano.ktestfarmatodo.presentation.viewModel.detail

class DetailInteractor{
    interface onGetDetailCharacterData{
        fun onGetDataItemsError()
        fun onGetDataItemsSucces()
    }

    fun detailCharacterData(name: String, description: String, image: String, listener: onGetDetailCharacterData){

        when {
            name.isEmpty() -> listener.onGetDataItemsError()
            description.isEmpty() -> listener.onGetDataItemsError()
            image.isEmpty() -> listener.onGetDataItemsError()
            else -> listener.onGetDataItemsSucces()
        }
    }
}