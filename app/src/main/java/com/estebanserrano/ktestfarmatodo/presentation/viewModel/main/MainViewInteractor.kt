package com.estebanserrano.ktestfarmatodo.presentation.viewModel.main

import com.estebanserrano.ktestfarmatodo.utils.Calculator
import com.estebanserrano.ktestfarmatodo.utils.Multiple

class MainViewInteractor {

    lateinit var calculator:Calculator
    lateinit var multiple: Multiple
    interface OnMainFinishedListener {
        fun onOperationError(error: String)
        fun onSuccess(number: String)
        fun onfindTheMultiple(number: String)
    }

    fun doOperation(number: String, listener: OnMainFinishedListener) {

        calculator = Calculator()
        val operationResult: String = calculator.resolveOperation(number)
        if (operationResult == Calculator.CONSTANT_ERROR_NUMBER ) {
            listener.onOperationError(operationResult)
        } else {
            listener.onSuccess(operationResult)
        }
    }

    fun findTheMultiple(number: String, listener: OnMainFinishedListener){

        multiple = Multiple()
        listener.onfindTheMultiple(multiple.findMultipe(number))
    }

}