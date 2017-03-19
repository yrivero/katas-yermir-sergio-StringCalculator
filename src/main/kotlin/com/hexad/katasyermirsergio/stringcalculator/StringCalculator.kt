package com.hexad.katasyermirsergio.stringcalculator

class StringCalculator {

    fun add(input: String): Int {
        if(input.isEmpty()){
            return 0
        }

        val numbers: List<Int> = parseInput(input)
        val result = sumNumbers(numbers)

        return result
    }

    private fun parseInput(input: String): List<Int> {
        var inputData: Array<String> = parseInputData(input)
        val numberList = inputData[1]
        val splittingRegex = inputData[0]


        val numbers: List<Int> = parseNumbers(numberList, splittingRegex)
        return numbers
    }

    private fun parseNumbers(input : String, splittingRegex : String) : List<Int>{
        val numbers : List<String> = input.split(Regex(splittingRegex))
        return numbers.map { x -> parseValidNumber(x)}
    }

    private fun parseValidNumber(x: String): Int {
        val intNumber = x.toInt()

        if (intNumber < 0) {
            throw NegativeNumberException("The number can't be negative : " + intNumber)
        } else {
            return intNumber
        }
    }

    private fun sumNumbers(input : List<Int>) : Int{
        return input.reduce { sumTotal, number -> sum(sumTotal,number) }
    }

    private fun sum(sumTotal: Int, number: Int): Int {
       if(number > 1000){
           return sumTotal
       }
       else{
           return sumTotal + number
       }
    }


    private fun parseInputData(input: String): Array<String> {

        val decomposedInputString: Array<String?> = decomposeInputString(input)

        val altDelimSpec = decomposedInputString[0]
        val numberList = decomposedInputString[1]

        val splittingRegex = buildSplittingRegex(altDelimSpec)

        return arrayOf(splittingRegex, numberList!!)

    }

    private fun buildSplittingRegex(altDelimSpec: String?): String {
        if (altDelimSpec == null) {
            return ",|\n"
        } else {
            var delimiterToken  = altDelimSpec.substring(2)
            if (delimiterToken.startsWith("[") && delimiterToken.endsWith("]")) {
                delimiterToken = delimiterToken.substring(1, delimiterToken.length - 1)
            }

            return "\\Q" + delimiterToken + "\\E"
        }
    }

    private fun decomposeInputString(input: String): Array<String?> {
        if (input.startsWith("//")) {
            val newLinePos = input.indexOf("\n")

            val altDelimSpec = input.substring(0, newLinePos)
            val numberList = input.substring(newLinePos+1)

            return arrayOf(altDelimSpec, numberList)
        }
        else {
            return arrayOf(null, input)
        }
    }

    class NegativeNumberException(message: String) : IllegalArgumentException(message)

}
