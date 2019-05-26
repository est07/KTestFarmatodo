package com.estebanserrano.ktestfarmatodo.presentation

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.estebanserrano.ktestfarmatodo.R
import com.estebanserrano.ktestfarmatodo.presentation.adapter.CharacterAdapter
import com.estebanserrano.ktestfarmatodo.data.service.CharacterServicesImpl
import com.estebanserrano.ktestfarmatodo.domain.useCase.GetCharacterServiceUseCase
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.ScreenState
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.List.CharactersViewModel
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.List.ListViewModelFactory
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.List.ListViewState
import com.estebanserrano.ktestfarmatodo.domain.model.Character
import kotlinx.android.synthetic.main.activity_list.progressBar
import kotlinx.android.synthetic.main.activity_list.recycleView
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {


    private val getCharacterServiceUseCase = GetCharacterServiceUseCase(CharacterServicesImpl())

    private lateinit var viewModel: CharactersViewModel
    private val count = 1
    companion object {
        val CHARACTER_DATA_NAME = "characterDataName"
        val CHARACTER_DATA_DESCRIPTION = "characterDataDescription"
        val CHARACTER_DATA_IMAGE = "characterDataImage"
    }

    var adapter = CharacterAdapter { character ->
        goToDetailActivity(character) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        viewModel = ViewModelProviders.of(
            this,
            ListViewModelFactory(getCharacterServiceUseCase)
        )[CharactersViewModel::class.java]
        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        viewModel.getCharacters("characters")
        recycleView.layoutManager = GridLayoutManager(this, count) as RecyclerView.LayoutManager?
        recycleView.adapter = adapter

    }

    private fun updateUI(screenState: ScreenState<ListViewState>?) {
        when (screenState) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenState.renderState)
        }
    }

    private fun processRenderState(renderState: ListViewState) {
        hideProgress()
        when (renderState) {
            is ListViewState.ShowItems -> showCharacters(renderState.items)
            is ListViewState.ShowMessage -> displayErrorMessage()
        }
    }

    private fun showCharacters(characters: List<Character>) {
        adapter.data = characters
    }

    private fun showProgress() {
        hideTextNoDataFound()
        progressBar.visibility = View.VISIBLE
        recycleView.visibility = View.GONE
    }

    private fun hideProgress() {
        progressBar.visibility = View.GONE
        recycleView.visibility = View.VISIBLE
    }

    private fun showTextNoDataFound() {
        txvNoDataFound.visibility = View.VISIBLE
    }

    private fun hideTextNoDataFound() {
        txvNoDataFound.visibility = View.GONE
    }

    private fun displayErrorMessage() {
        showTextNoDataFound()
        val snackbar = Snackbar.make(charactersActivity,getString(R.string.network_error), Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(R.string.retry)) {
                viewModel.getCharacters("characters")
            }

        snackbar.show()
    }

    private fun goToDetailActivity(itemCharacter: Character) {

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(CHARACTER_DATA_NAME, itemCharacter.name)
            putExtra(CHARACTER_DATA_DESCRIPTION, itemCharacter.description)
            putExtra(CHARACTER_DATA_IMAGE, itemCharacter.thumbnail.path + "." + itemCharacter.thumbnail.extension)
        }
        startActivity(intent)
    }

}
