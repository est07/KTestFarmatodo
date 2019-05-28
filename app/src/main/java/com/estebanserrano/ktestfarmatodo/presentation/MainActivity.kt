package com.estebanserrano.ktestfarmatodo.presentation

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.estebanserrano.ktestfarmatodo.R
import com.estebanserrano.ktestfarmatodo.presentation.extension.showToast
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.ScreenState
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewInteractor
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewModel
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewModelFactory
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewState
import com.estebanserrano.ktestfarmatodo.utils.Calculator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CONSTANT_STRING_NUMBER_ZERO = "0"

    private lateinit var viewModel : MainViewModel

    var operationResult:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProviders.of(
            this,
            MainViewModelFactory(MainViewInteractor())
        )[MainViewModel::class.java]

        viewModel.mainState.observe(::getLifecycle, ::updateUI)
    }

    private fun updateUI(screenState: ScreenState<MainViewState>?) {
        when(screenState){
            is ScreenState.Render ->processOperationState(screenState.renderState)
        }
    }

    private fun processOperationState(renderState: MainViewState) {

        when (renderState) {
            is MainViewState.ShowResult -> showOperationResult(renderState.result)
            is MainViewState.ShowMultipleResult ->showListActivity(renderState.result)
        }
    }

    private fun showOperationResult(result: String) {

        operationResult = result

        this.showToast("El resultado de la operacion es: $result")

        if(result == CONSTANT_STRING_NUMBER_ZERO || result == Calculator.CONSTANT_ERROR_NUMBER){
            goToListMarvelActivity(result, result)
        }else{
            viewModel.onfindMultiple(result)
        }
    }

    private fun showListActivity(result: String) {
        this.showToast("El resultado es multiplo de: ${result}")
        goToListMarvelActivity(operationResult, result)
    }

    fun onClickBtnClean(view: View) {
        edtxtOperation.setText("")
    }

    fun onClickBtnEquals(view: View) {
        onEqualsClicked()
    }

    private fun onEqualsClicked() {
        viewModel.onEqualsClicked(edtxtOperation.text.toString())
    }

    fun goToListMarvelActivity(resultNumber: String, multipleNUmber:String) {

        val intent = Intent(this, ListMarvelActivity::class.java).apply {
            putExtra(ListMarvelActivity.RESULT_NUMBER, resultNumber)
            putExtra(ListMarvelActivity.MULTIPLE_NUMBER, multipleNUmber)
        }
        startActivity(intent)
    }
}
