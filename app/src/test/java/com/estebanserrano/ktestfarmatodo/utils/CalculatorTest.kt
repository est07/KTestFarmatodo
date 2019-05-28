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
        val input = "-9- (-3)"
        val result = calculator.resolveOperation(input)
        val expected = "-6"
        assertEquals(expected, result)
    }

    @Test
    fun sumOperation() {
        val input = "-9 + -(3)"
        val result = calculator.resolveOperation(input)
        val expected = "-12"
        assertEquals(expected, result)
    }

    @Test
    fun multiplicationOperation() {
        val input = "(-9) *3"
        val result = calculator.resolveOperation(input)
        val expected = "-27"
        assertEquals(expected, result)
    }

    @Test
    fun divisionErrorOperation() {
        val input = "-9 / - (3)"
        val result = calculator.resolveOperation(input)
        val expected = "Operation error"
        assertEquals(expected, result)
    }

    @Test
    fun divisionOperation() {
        val input = "-9 / -(3)"
        val result = calculator.resolveOperation(input)
        val expected = "3"
        assertEquals(expected, result)
    }
}