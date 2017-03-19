package com.hexad.katasyermirsergio.stringcalculator

class StringCalculator {

    fun add(input: String): Int {
        if(input.isEmpty()){
            return 0
        }

        val numbers : List<String> = input.split(",")

        val totalSum : Int = numbers
                        .map { x -> x.toInt()}
                        .reduce { sum, number -> sum + number }

        return totalSum
    }

}