package com.estebanserrano.ktestfarmatodo

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.estebanserrano.ktestfarmatodo.extension.getImageByUrl
import com.estebanserrano.ktestfarmatodo.extension.getImageDetailByUrl
import com.estebanserrano.ktestfarmatodo.viewModel.ScreenState
import com.estebanserrano.ktestfarmatodo.viewModel.detail.DetailInteractor
import com.estebanserrano.ktestfarmatodo.viewModel.detail.DetailViewModel
import com.estebanserrano.ktestfarmatodo.viewModel.detail.DetailViewModelFactory
import com.estebanserrano.ktestfarmatodo.viewModel.detail.DetailViewState
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    private lateinit var viewModel : DetailViewModel
    var name:String = ""
    var description:String = ""
    var imageURL:String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        viewModel = ViewModelProviders.of(
            this, DetailViewModelFactory(DetailInteractor())
        )[DetailViewModel::class.java]
        getIntentData()

        viewModel.detailState.observe(::getLifecycle, ::updateUI)

        viewModel.onGetDataItems(name, description,name)

    }

    private fun updateUI(screenState: ScreenState<DetailViewState>?) {
        when(screenState){
            is ScreenState.Render ->processDetailState(screenState.renderState)
        }
    }

    private fun processDetailState(renderState: DetailViewState) {

        when (renderState) {
            DetailViewState.Success -> showDataCharacter()
            DetailViewState.CharacterDataError -> showDataCharacterError()
        }
    }

    private fun getIntentData(){

        intent.let {
            name = it.getStringExtra(CharactersActivity.CHARACTER_DATA_NAME)
            description = it.getStringExtra(CharactersActivity.CHARACTER_DATA_DESCRIPTION)
            imageURL = it.getStringExtra(CharactersActivity.CHARACTER_DATA_IMAGE)

        }
    }

    private fun showDataCharacter(){
        txvCharacterName.text = name
        txvCharacterDescription.text = description
        imvCharacterImage.getImageByUrl(imageURL)
    }

    private fun showDataCharacterError(){
        txvCharacterName.text = name
        txvCharacterDescription.text = description
        imvCharacterImage.getImageDetailByUrl(imageURL)
    }
}
