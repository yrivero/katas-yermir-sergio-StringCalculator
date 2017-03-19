package com.hexad.katasyermirsergio.stringcalculator

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
class StringCalculatorTest {

    var calculator : StringCalculator? = null

    @Before
    fun setup(){
        calculator = StringCalculator()
    }

    @Test
    fun canCreateInstanceOfStringCalculator() {
    }

    @Test
    fun whenAddingEmptyStringMustReturn0() {
        assertResultEquals("When adding empty string the result must be zero", "", 0)
    }

    @Test
    fun whenAddingASingleNumberMustReturnThatNumber() {
        assertResultEquals("When adding a single number the result must be that number", "1", 1)
    }

    @Test
    fun whenAddingTwoNumbersMustReturnTheirSum() {
        assertResultEquals("When adding two numbers the result must be their sum", "1,2", 3)
    }


    private fun assertResultEquals(message : String, input : String, expectedResult : Int) {
        val result = calculator?.add(input)
        assertEquals(message, expectedResult, result)
    }
}


