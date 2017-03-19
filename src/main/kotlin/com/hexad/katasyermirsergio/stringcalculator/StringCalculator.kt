package com.hexad.katasyermirsergio.stringcalculator

import java.util.regex.Pattern

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
        if (isStandardDelimitersSpec(altDelimSpec)) {
            // No spec. Standard delimiters
            return buildStandardSplittingRegex()
        }
        else if (isSimpleDelimiterFormatDelimSpec(altDelimSpec)) {
            return buildSplittingRegexForSimpleDelimiterFormat(altDelimSpec)
        }
        else {
            return buildSplittingRegexForLongDelimiterFormat(altDelimSpec)
        }
    }


    private fun isStandardDelimitersSpec(altDelimSpec: String?) = altDelimSpec == null

    private fun buildStandardSplittingRegex(): String {
        return ",|\n"
    }

    private fun isSimpleDelimiterFormatDelimSpec(altDelimSpec: String?) : Boolean {
        return altDelimSpec!=null && altDelimSpec.startsWith("//") && !altDelimSpec.startsWith("//[")
    }

    private fun buildSplittingRegexForSimpleDelimiterFormat(altDelimSpec: String?): String {
        // Simple format
        var delimiterToken = altDelimSpec!!.substring(2)
        return "\\Q" + delimiterToken + "\\E"
    }

    private fun buildSplittingRegexForLongDelimiterFormat(altDelimSpec: String?): String {
        // Long delimiter format

        //   ".*?" lazy
        //   ".*" greedy, eager
        var pattern = Pattern.compile("\\[" + "(.*?)" + "\\]")
        var matcher = pattern.matcher(altDelimSpec)

        var splittingRegex = StringBuilder()

        var delimiterToken = ""
        while (matcher.find()){
            delimiterToken = matcher.group(1)
            //if the delimiter is an empty string we can ignore it
            if(delimiterToken.isEmpty()){
                continue
            }

            if(splittingRegex.length > 0){
                splittingRegex.append("|")
            }

            splittingRegex.append("\\Q" + delimiterToken + "\\E")
        }

        return splittingRegex.toString()
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
