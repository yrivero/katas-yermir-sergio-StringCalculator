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

    @Test
    fun whenAddingNNumbersMustReturnTheirSum() {
        assertResultEquals("When adding N numbers the result must be their sum", "1,2,3,4,5,6,7,8,9,10", 55)
    }

    @Test
    fun inputNumbersCanBeSeparatedByCommaOrNewLine() {
        assertResultEquals("When adding N numbers separated with comma or new line the result must be their sum", "1\n2,3\n4,5\n6,7\n8,9\n10", 55)
    }

    @Test
    fun canDefineANewDelimiterToSeparateTheNumbers() {
        assertResultEquals(
                "When changing the delimiter the numbers are separated using it",
                "//;\n" +
                    "1;2;3;4;5;6;7;8;9;10",
                55)
    }

    private fun assertResultEquals(message : String, input : String, expectedResult : Int) {
        val result = calculator?.add(input)
        assertEquals(message, expectedResult, result)
    }
}


