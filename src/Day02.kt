fun main() {
    fun power(counts: Map<String, Int>): Int {
        return counts.values.reduce { acc, i -> i * acc }
    }

    fun part1(input: List<String>): Int {
        val maxValid = mapOf(
                "red" to 12,
                "green" to 13,
                "blue" to 14,
        )

        val validIds = input.mapNotNull { line ->
            val id = Regex("""\d+""").find(line)!!.value.toInt()

            val colourCounts = Regex("""(\d+) (\w+)""").findAll(line)
            val isValid = colourCounts.all {
                val (count, colour) = it.destructured

                val maxValidForThisColour = maxValid[colour.trim()]!!

                count.toInt() <= maxValidForThisColour
            }

            if (isValid) id else null
        }

        return validIds.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        val powers = input.map { line ->
            val id = Regex("""\d+""").find(line)!!.value.toInt()

            val colourCounts = Regex("""(\d+) (\w+)""").findAll(line)

            colourCounts
                    .fold(
                            mapOf<String, Int>()
                    )
                    { accumulator, element ->
                        val (count, colour) = element.destructured

                        val maxCount = maxOf(accumulator.getOrDefault(colour.trim(), 0), count.toInt())

                        accumulator + (colour.trim() to maxCount)
                    }
        }.map(::power)

        return powers.sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    println(part1(testInput))
    check(part1(testInput) == 8)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
