package com.estebanserrano.ktestfarmatodo.utils

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class MultipleTest {

    lateinit var multiple: Multiple
    @Before
    fun setUp() {
        multiple = Multiple()
    }

    @Test
    fun findMultipleOfThree() {
        val input = "-12"
        val result = multiple.findMultipe(input)
        val expected = "3"
        assertEquals(expected, result)
    }

    @Test
    fun findMultipleOfFive() {
        val input = "25"
        val result = multiple.findMultipe(input)
        val expected = "5"
        assertEquals(expected, result)
    }

    @Test
    fun findMultipleOfSeven() {
        val input = "133"
        val result = multiple.findMultipe(input)
        val expected = "7"
        assertEquals(expected, result)
    }

    @Test
    fun findMultipleOfEleven() {
        val input = "143"
        val result = multiple.findMultipe(input)
        val expected = "11"
        assertEquals(expected, result)
    }

    @Test
    fun findMultipleOfThirteen() {
        val input = "299"
        val result = multiple.findMultipe(input)
        val expected = "13"
        assertEquals(expected, result)
    }

    @Test
    fun findErrorMultiple() {
        val input = "-1333"
        val result = multiple.findMultipe(input)
        val expected = "Operation error"
        assertEquals(expected, result)
    }



}