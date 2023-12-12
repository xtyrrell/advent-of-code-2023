fun main() {
    val wordsDigits = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val numberDigits = (1..9).map { it.toString() }

    fun part1(input: List<String>): Int {
        val ans = input.map {
            val first = it.find { it.isDigit() }!!
            val last = it.findLast { it.isDigit() }!!

            "$first$last"
        }

        return ans.sumOf { it.toInt() }
    }

    fun part2(input: List<String>): Int {
        val ans = input.map { line ->
            val (_, first) = line.findAnyOf(wordsDigits + numberDigits)!!
            val (_, last) = line.findLastAnyOf(wordsDigits + numberDigits)!!

            val firstNumber = if (first in wordsDigits) wordsDigits.indexOf(first) + 1 else first
            val lastNumber = if (last in wordsDigits) wordsDigits.indexOf(last) + 1 else last

            "$firstNumber$lastNumber"
        }

        return ans.sumOf { it.toInt() }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    println(part1(testInput))
    check(part1(testInput) == 112)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
