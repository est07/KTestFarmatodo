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
import com.estebanserrano.ktestfarmatodo.data.service.*
import com.estebanserrano.ktestfarmatodo.presentation.adapter.ListMarvelAdapter
import com.estebanserrano.ktestfarmatodo.domain.model.MarvelCard
import com.estebanserrano.ktestfarmatodo.domain.useCase.*
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.ScreenState
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.list.ListMarvelViewModel
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.list.ListViewModelFactory
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.list.ListMarvelViewState
import kotlinx.android.synthetic.main.activity_list.progressBar
import kotlinx.android.synthetic.main.activity_list.recycleView
import kotlinx.android.synthetic.main.activity_list.*

class ListMarvelActivity : AppCompatActivity() {

    companion object {

        var NUMBER_ZERO = "0"
        var NUMBER_THREE_ = "3"
        var NUMBER_FIVE_ = "5"
        var NUMBER_SEVEN = "7"
        var NUMBER_EVENTS = "11"
        var NUMBER_THIRTEEN = "13"
        val RESULT_NUMBER = "resultNumber"
        val MULTIPLE_NUMBER = "multipleNumber"
        val ITEM_DATA_HEADER = "listDataHeader"
        val ITEM_DATA_DESCRIPTION = "listDataDescription"
        val ITEM_DATA_IMAGE = "listDataImage"
    }

    private lateinit var marvelViewModel: ListMarvelViewModel
    private val count = 1
    private var resultNumber:String = ""
    private var multipleNumber:String = ""

    var adapter = ListMarvelAdapter { character ->
        goToDetailActivity(character) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        getIntentData()

        marvelViewModel = ViewModelProviders.of(
            this,
            ListViewModelFactory(getMarvelUseCase(multipleNumber))
        )[ListMarvelViewModel::class.java]
        marvelViewModel.listMarvelState.observe(::getLifecycle, ::updateUI)
        marvelViewModel.getCharacters()
        recycleView.layoutManager = GridLayoutManager(this, count) as RecyclerView.LayoutManager?
        recycleView.adapter = adapter
    }

    private fun updateUI(screenStateMarvel: ScreenState<ListMarvelViewState>?) {
        when (screenStateMarvel) {
            ScreenState.Loading -> showProgress()
            is ScreenState.Render -> processRenderState(screenStateMarvel.renderState)
        }
    }

    private fun processRenderState(renderStateMarvel: ListMarvelViewState) {
        hideProgress()
        when (renderStateMarvel) {
            is ListMarvelViewState.ShowItems -> showCharacters(renderStateMarvel.items)
            is ListMarvelViewState.ShowMessage -> displayErrorMessage()
        }
    }

    private fun getIntentData(){

        intent.run {
            resultNumber = getStringExtra(ListMarvelActivity.RESULT_NUMBER)
            multipleNumber = getStringExtra(ListMarvelActivity.MULTIPLE_NUMBER)
        }
    }

    private fun showCharacters(characters: List<MarvelCard>) {
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
                marvelViewModel.getCharacters()
            }

        snackbar.show()
    }

    fun getMarvelUseCase(multipleNumber:String):BaseServiceUseCase{
        lateinit var useCase: BaseServiceUseCase
        when (multipleNumber) {
            NUMBER_ZERO -> useCase = GetCharacterServiceUseCase(CharactersServicesImpl())
            NUMBER_THREE_ -> useCase = GetComicsServiceUseCase(ComicSerivicesImpl())
            NUMBER_FIVE_ -> useCase = GetComicsServiceUseCase(ComicSerivicesImpl())
            NUMBER_SEVEN -> useCase = GetCreatorsServiceUseCase(CreatorsServiceImpl())
            NUMBER_EVENTS -> useCase = GetEventsServiceUseCase(EventsServiceImpl())
            NUMBER_THIRTEEN -> useCase = GetSeriesServiceUseCase(SeriesServicesImpl())
            else -> useCase = GetSeriesServiceUseCase(SeriesServicesImpl())
        }

        return useCase
    }

    private fun goToDetailActivity(itemListMavel: MarvelCard) {

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(ITEM_DATA_HEADER, itemListMavel.header)
            putExtra(ITEM_DATA_DESCRIPTION, itemListMavel.description)
            putExtra(ITEM_DATA_IMAGE, itemListMavel.thumbnail.path + "." + itemListMavel.thumbnail.extension)
        }
        startActivity(intent)
    }

}
