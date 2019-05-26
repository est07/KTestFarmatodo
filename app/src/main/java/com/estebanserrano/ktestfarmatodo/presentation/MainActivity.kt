package com.estebanserrano.ktestfarmatodo.presentation

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.estebanserrano.ktestfarmatodo.R
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.ScreenState
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewInteractor
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewModel
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewModelFactory
import com.estebanserrano.ktestfarmatodo.presentation.viewModel.main.MainViewState
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val CONSTANT_NUMBER_ZERO = 0
    private val CONSTANT_NUMBER_ONE = 1

    private lateinit var viewModel : MainViewModel

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
        }
    }

    private fun showOperationResult(result: String) {

    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberOne(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_one))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberTwo(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_two))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberThree(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_three))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberFour(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_four))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberFive(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_five))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberSix(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_six))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberSeven(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_seven))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberEight(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_eight))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberNine(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_nine))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnNumberZero(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.number_zero))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnOpenParenthesis(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.sign_open_parenthesis))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnCloseParenthesis(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.sign_close_parenthesis))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnSum(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.sign_sum))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnSubtraction(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.sign_subtraction))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnMultiplication(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.sign_multiplication))
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnDivision(view: View) {
        edtxtOperation.setText(getStringEditText()+ getString(R.string.sing_division))
    }

    fun onClickBtnDelete(view: View) {

        if (edtxtOperation.text.toString().length> CONSTANT_NUMBER_ZERO)
            edtxtOperation.setText(deleteLastCharacter(edtxtOperation.text.toString()))
    }

    fun onClickBtnClean(view: View) {
        edtxtOperation.setText("")
    }

    @SuppressLint("SetTextI18n")
    fun onClickBtnSpace(view: View) {
        edtxtOperation.setText(getStringEditText()+ " ")
    }

    fun onClickBtnEquals(view: View) {

        onEqualsClicked()
    }

    private fun getStringEditText(): String {

        return edtxtOperation.text.toString()
    }

    fun deleteLastCharacter(data: String): String {
        return data.substring(CONSTANT_NUMBER_ZERO, data.length - CONSTANT_NUMBER_ONE)
    }

    private fun onEqualsClicked() {
        viewModel.onEqualsClicked(edtxtOperation.text.toString())
    }
}
