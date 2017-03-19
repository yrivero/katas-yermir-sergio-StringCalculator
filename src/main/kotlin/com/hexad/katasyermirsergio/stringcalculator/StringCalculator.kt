package com.hexad.katasyermirsergio.stringcalculator

class StringCalculator {

    fun add(input: String): Int {
        if(input.isEmpty()){
            return 0
        }

        var inputData : Array<String> = parseInputData(input)
        val numberList = inputData[1]
        val splittingRegex = inputData[0]

        val numbers : List<Int> = parseNumbers(numberList, splittingRegex)
        val result = sumNumbers(numbers)

        return result
    }


    fun parseNumbers(input : String, splittingRegex : String) : List<Int>{
        val numbers : List<String> = input.split(Regex(splittingRegex))
        return numbers.map { x -> x.toInt()}
    }

    fun sumNumbers(input : List<Int>) : Int{
        return input.reduce { sum, number -> sum + number }
    }

    private fun parseInputData(input: String): Array<String> {
        if (input.startsWith("//")) {
            val newLinePos = input.indexOf("\n")
            val delimiter = input.substring(2, newLinePos)

            val numberList = input.substring(newLinePos+1)

            return arrayOf(delimiter, numberList)
        }
        else {
            return arrayOf(",|\n", input)
        }
    }



}
