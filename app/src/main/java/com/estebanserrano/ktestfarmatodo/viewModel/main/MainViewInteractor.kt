package com.estebanserrano.ktestfarmatodo.viewModel.main

import java.util.regex.Pattern

class MainViewInteractor {

    companion object {

        private var counterSign: Int = 0

        private val CONSTANT_NUMBER_ZERO = 0
        private val CONSTANT_NUMBER_ONE = 1
        private val CONSTANT_NUMBER_TWO = 2
        private val CONSTANT_NUMBER_THREE = 3

        private val CONSTANT_ERROR_NUMBER = "Operation error"
        private val CONSTANT_SIGN_EMPTY = ""
        private val CONSTANT_SIGN_SPACE = " "
        private val CONSTANT_SIGN_ERROR = "[()+/*-]"
        private val CONSTANT_PARENTHESIS = "[()]+"
        private val CONSTANT_SEPARATE_SUBTRACTION_SIGN = "(?=-)"

        private val CONSTANT_SIGN_SUM = "+"
        private val CONSTANT_SIGN_SUBTRACTION = "-"
        private val CONSTANT_SIGN_DIVISION = "/"
        private val CONSTANT_SIGN_MULTIPLICATION = "*"

        private val CONSTANT_SIGN_SUBTRACTION_AND_SPACE = "- "

        private val CONSTANT_SIGN_OPEN_PARENTHESIS_AND_SUBTRACTION = "(-"

        private var positionNumberOne = 0
        private var positionNumbertwo = 0

    }

    interface OnMainFinishedListener {
        fun onOperationError(error: String)
        fun onSuccess(number: String)
    }

    fun doOperation(number: String, listener: OnMainFinishedListener) {

        val operationResult: String = resolveOperation(number)
        if (operationResult == CONSTANT_ERROR_NUMBER ) {
            listener.onOperationError(operationResult)
        } else {
            listener.onSuccess(operationResult)
        }
    }

    private fun resolveOperation(data: String): String {
        var operationResult = CONSTANT_SIGN_EMPTY

        if (!findCharactersError(data)) {
            if (!validateSignSum(data)) {
                if (!validateSignMultiplication(data)) {
                    operationResult = if (!validateSignDivision(data)) {
                        if (!validateSingSubtraction(data)) {
                            CONSTANT_ERROR_NUMBER
                        } else {
                            doOperationSubtraction(data, CONSTANT_SIGN_SUBTRACTION)
                        }
                    } else {
                        if (!counter()) {
                            doOperationSunOrMultiplicationOrDivision(data, CONSTANT_SIGN_DIVISION)
                        } else {
                            CONSTANT_ERROR_NUMBER
                        }
                    }
                } else {
                    operationResult = if (!counter()) {
                        doOperationSunOrMultiplicationOrDivision(data, CONSTANT_SIGN_MULTIPLICATION)
                    } else {
                        CONSTANT_ERROR_NUMBER
                    }
                }
            } else {
                operationResult = if (!counter()) {
                    doOperationSunOrMultiplicationOrDivision(data, CONSTANT_SIGN_SUM)
                } else {
                    CONSTANT_ERROR_NUMBER
                }
            }
        } else {
            operationResult = CONSTANT_ERROR_NUMBER
        }
        return operationResult
    }

    private fun resolveSpaces(data: String): String {
        val number: String
        val result = data.split(CONSTANT_SIGN_SPACE.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (findErrorSubtractionSign(result)) {
            number = CONSTANT_ERROR_NUMBER
        } else {
            number = separateParentheses(findNumber(result))
        }
        return number
    }

    private fun validateSignSum(operation: String): Boolean {
        var thereIsResult = false
        val separador = Pattern.quote(CONSTANT_SIGN_SUM)
        val result = operation.split(separador.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        counterSign = result.size
        if (result.size > CONSTANT_NUMBER_ONE)
            thereIsResult = true

        return thereIsResult
    }

    private fun validateSingSubtraction(operation: String): Boolean {
        var thereIsResult = false
        val separador = Pattern.quote(CONSTANT_SIGN_SUBTRACTION)
        val result = operation.split(separador.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        counterSign = result.size
        if (result.size > CONSTANT_NUMBER_ONE)
            thereIsResult = true
        return thereIsResult
    }

    private fun validateSignMultiplication(operation: String): Boolean {
        var thereIsResult = false
        val separador = Pattern.quote(CONSTANT_SIGN_MULTIPLICATION)
        val result = operation.split(separador.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        counterSign = result.size
        if (result.size > CONSTANT_NUMBER_ONE)
            thereIsResult = true
        return thereIsResult
    }

    private fun validateSignDivision(operation: String): Boolean {
        var thereIsResult = false
        val separador = Pattern.quote(CONSTANT_SIGN_DIVISION)
        val result = operation.split(separador.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        counterSign = result.size
        if (result.size > CONSTANT_NUMBER_ONE)
            thereIsResult = true
        return thereIsResult
    }

    private fun counter(): Boolean {

        var thereIsResult = false
        if (counterSign > CONSTANT_NUMBER_TWO) {
            thereIsResult = true
        }
        return thereIsResult
    }

    private fun doOperationSunOrMultiplicationOrDivision(data: String, sign: String): String {
        var operationResult: String
        val result = data.split(Pattern.quote(sign).toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (findErrorFirstNumber(result[CONSTANT_NUMBER_ZERO])) {
            operationResult = CONSTANT_ERROR_NUMBER
        } else {
            if (resolveSpaces(result[CONSTANT_NUMBER_ZERO]) != CONSTANT_ERROR_NUMBER && resolveSpaces(result[CONSTANT_NUMBER_ONE]) != CONSTANT_ERROR_NUMBER) {
                try {
                    positionNumberOne = Integer.parseInt(resolveSpaces(result[CONSTANT_NUMBER_ZERO]))
                    positionNumbertwo = Integer.parseInt(resolveSpaces(result[CONSTANT_NUMBER_ONE]))
                    operationResult = calculateResult(sign)
                } catch (e: NumberFormatException) {
                    operationResult = CONSTANT_ERROR_NUMBER
                }

            } else {
                operationResult = CONSTANT_ERROR_NUMBER
            }
        }
        return operationResult
    }

    private fun findErrorSubtractionSign(result: Array<String>): Boolean {
        var thereIsResult = false
        for (data in result) {
            if (data == CONSTANT_SIGN_SUBTRACTION || data == CONSTANT_SIGN_OPEN_PARENTHESIS_AND_SUBTRACTION)
                thereIsResult = true
        }

        return thereIsResult
    }

    private fun findErrorFirstNumber(result: String): Boolean {
        var thereIsResult = false
        if (result == CONSTANT_SIGN_EMPTY || result == CONSTANT_SIGN_SPACE)
            thereIsResult = true

        return thereIsResult
    }

    private fun findNumber(result: Array<String>): String {
        var number = CONSTANT_SIGN_EMPTY
        for (data in result) {
            if (data != CONSTANT_SIGN_EMPTY)
                number += data
        }
        return number
    }

    private fun findCharactersError(data: String): Boolean {
        var error = false
        val result = data.split(CONSTANT_SIGN_ERROR.toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        if (result.size == CONSTANT_NUMBER_ZERO)
            error = true

        return error
    }

    private fun doOperationSubtraction(data: String, sign: String): String {
        var operationResult = CONSTANT_SIGN_EMPTY
        val operation = removeOperationParenthesis(data)

        if (findErrorSingSubtraction(operation) >= CONSTANT_NUMBER_TWO) {
            operationResult = CONSTANT_ERROR_NUMBER
        } else {

            val componentsNumber = countComponentsSubtraction(operation)

            if (componentsNumber > CONSTANT_NUMBER_THREE) {
                operationResult = CONSTANT_ERROR_NUMBER
            } else if (componentsNumber == CONSTANT_NUMBER_THREE) {

                val result = operation.split(CONSTANT_SEPARATE_SUBTRACTION_SIGN.toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()

                if (findBlankSpace(result)) {

                    try {
                        positionNumberOne = Integer.parseInt(resolveSpaces(result[CONSTANT_NUMBER_ONE]))
                        positionNumbertwo = Integer.parseInt(resolveSpaces(result[CONSTANT_NUMBER_THREE]))
                        operationResult = calculateResult(sign)
                    } catch (e: NumberFormatException) {
                        operationResult = CONSTANT_ERROR_NUMBER
                    }
                } else {
                    try {
                        positionNumberOne = Integer.parseInt(resolveSpaces(result[CONSTANT_NUMBER_ZERO]))
                        positionNumbertwo = Integer.parseInt(resolveSpaces(result[CONSTANT_NUMBER_TWO]))
                        operationResult = calculateResult(sign)
                    } catch (e: NumberFormatException) {
                        operationResult = CONSTANT_ERROR_NUMBER
                    }
                }

            } else if (componentsNumber == CONSTANT_NUMBER_TWO) {
                val result = operation.split(CONSTANT_SEPARATE_SUBTRACTION_SIGN.toRegex())
                    .dropLastWhile { it.isEmpty() }.toTypedArray()

                try {
                    positionNumberOne = Integer.parseInt(resolveSpaces(result[CONSTANT_NUMBER_ZERO]))
                    positionNumbertwo = Integer.parseInt(resolveSpaces(findSubtractionNumber(result[CONSTANT_NUMBER_ONE])))
                    operationResult = calculateResult(sign)
                } catch (e: NumberFormatException) {
                    operationResult = CONSTANT_ERROR_NUMBER
                }
            } else if (componentsNumber == CONSTANT_NUMBER_ONE) {

                try {
                    positionNumberOne = Integer.parseInt(resolveSpaces(operation))
                    positionNumbertwo = CONSTANT_NUMBER_ZERO
                    operationResult = calculateResult(sign)
                } catch (e: NumberFormatException) {
                    operationResult = CONSTANT_ERROR_NUMBER
                }
            }
        }
        return operationResult
    }

    private fun removeOperationParenthesis(data: String): String {
        var operation = CONSTANT_SIGN_EMPTY
        val result = data.split(CONSTANT_PARENTHESIS.toRegex())
            .dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in result.indices) {
            val operationPart = result[i]
            if (operationPart != CONSTANT_SIGN_EMPTY)
                operation += operationPart
        }
        return operation
    }

    private fun findErrorSingSubtraction(data: String): Int {
        var signs = CONSTANT_NUMBER_ZERO
        val result = data.split(CONSTANT_SEPARATE_SUBTRACTION_SIGN
            .toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in result.indices) {
            val operationPart = result[i]
            if (operationPart == CONSTANT_SIGN_SUBTRACTION_AND_SPACE || operationPart == CONSTANT_SIGN_SUBTRACTION)
                signs += CONSTANT_NUMBER_ONE
        }
        return signs
    }

    private fun countComponentsSubtraction(data: String): Int {
        var signs = CONSTANT_NUMBER_ZERO
        val result = data.split(CONSTANT_SEPARATE_SUBTRACTION_SIGN
            .toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (i in result.indices) {
            val operationPart = result[i]
            if (operationPart != CONSTANT_SIGN_EMPTY)
                signs += CONSTANT_NUMBER_ONE
        }
        return signs
    }

    private fun findBlankSpace(data: Array<String>): Boolean {
        var result = false
        for (i in data.indices) {
            val operationPart = data[i]
            if (operationPart == CONSTANT_SIGN_EMPTY)
                result = true
        }
        return result
    }

    private fun findSubtractionNumber(data: String): String {
        var number = CONSTANT_SIGN_EMPTY
        val result = data.split(CONSTANT_SIGN_SUBTRACTION
            .toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        for (operationPart in result) {
            if (operationPart != CONSTANT_SIGN_EMPTY)
                number = operationPart
        }
        return number
    }


    private fun separateParentheses(data: String): String {
        val number: String
        val result = data.split(CONSTANT_PARENTHESIS
            .toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        if (result.size == CONSTANT_NUMBER_ONE) {
            number = result[CONSTANT_NUMBER_ZERO]
        } else {
            number = result[CONSTANT_NUMBER_ZERO] + result[CONSTANT_NUMBER_ONE]
        }
        return number
    }

    private fun calculateResult(sign: String): String {

        val operationResult: String

        when (sign) {
            CONSTANT_SIGN_SUM -> {
                operationResult = (positionNumberOne + positionNumbertwo).toString()
            }
            CONSTANT_SIGN_SUBTRACTION -> {
                operationResult = (positionNumberOne - positionNumbertwo).toString()
            }
            CONSTANT_SIGN_MULTIPLICATION -> {
                operationResult = (positionNumberOne * positionNumbertwo).toString()
            }
            CONSTANT_SIGN_DIVISION -> {
                operationResult = (positionNumberOne / positionNumbertwo).toString()
            }
            else -> {
                operationResult = CONSTANT_ERROR_NUMBER
            }
        }
        return operationResult
    }

    fun isMultip(n1: Int, n2: Int): Boolean {

        return n1 % n2 == CONSTANT_NUMBER_ZERO
    }
}