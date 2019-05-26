package com.estebanserrano.ktestfarmatodo.presentation.viewModel.main

import com.estebanserrano.ktestfarmatodo.utils.Calculator
import java.util.regex.Pattern

class MainViewInteractor {

    lateinit var calculator:Calculator
    interface OnMainFinishedListener {
        fun onOperationError(error: String)
        fun onSuccess(number: String)
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

    fun isMultip(n1: Int, n2: Int): Boolean {

        return n1 % n2 == Calculator.CONSTANT_NUMBER_ZERO
    }
}