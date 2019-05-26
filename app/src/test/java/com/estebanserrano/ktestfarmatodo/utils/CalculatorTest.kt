package com.estebanserrano.ktestfarmatodo.utils

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class CalculatorTest {

    lateinit var calculator: Calculator
    @Before
    fun setUp() {
        calculator = Calculator()
    }

    @Test
    fun sustractionOperation() {
        val imput = "-9- (-3)"
        val result = calculator.resolveOperation(imput)
        val expected = "-6"
        assertEquals(expected, result)
    }
}