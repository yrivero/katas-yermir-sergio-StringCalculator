package com.hexad.katasyermirsergio.stringcalculator

class StringCalculator {

    fun add(input: String): Int {
        if(input.isEmpty()){
            return 0
        }

        val numbers : List<Int> = parseNumbers(input)
        val result = sumNumbers(numbers)

        return result
    }

    fun parseNumbers(input : String) : List<Int>{
        val numbers : List<String> = input.split(Regex(",|\n"))
        return numbers.map { x -> x.toInt()}
    }


    fun sumNumbers(input : List<Int>) : Int{
        return input.reduce { sum, number -> sum + number }
    }
}