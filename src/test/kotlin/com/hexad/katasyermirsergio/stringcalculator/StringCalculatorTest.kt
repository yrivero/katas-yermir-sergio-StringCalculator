package com.hexad.katasyermirsergio.stringcalculator

import org.junit.Test

import org.junit.Assert.*

class StringCalculatorTest {

    @Test
    fun canCreateInstanceOfStringCalculator() {
        StringCalculator()
    }

    @Test
    fun whenAddingEmptyStringMustReturn0() {
        val calculator = StringCalculator()
        val result = calculator.add("")

        assertEquals("When adding empty string the result must be zero", 0, result)
    }

    @Test
    fun whenAddingASingleNumberMustReturnThatNumber() {
        val calculator = StringCalculator()
        val result = calculator.add("1")

        assertEquals("When adding a single number the result must be that number", 1, result)
    }
}

