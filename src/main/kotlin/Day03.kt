import java.io.File

fun main() {
    val lines = File("src/main/kotlin", "Day03.txt")
        .readLines()

    val mostCommonBits = getMostCommonBits(lines)
    val gamma = mostCommonBits.toDecimal()
    val epsilon = mostCommonBits.toFlippedDecimal()
    println("Part One: ${gamma * epsilon}")

    val oxygenRating = getLifeSupportRating(lines, 1).toDecimal()
    val co2Rating = getLifeSupportRating(lines, 0).toDecimal()
    println("Part Two: ${oxygenRating * co2Rating}")
}

private fun getLifeSupportRating(
    inputLines: List<String>,
    bitCriteria: Int,
    index: Int = 0
): List<Int> {

    val bitsForCriteria = if (bitCriteria == 1) {
        getMostCommonBits(inputLines)
    } else {
        getLeastCommonBits(inputLines)
    }

    val (matchingLines, mismatchedLines) =
        inputLines.partition { it[index].toString() == bitsForCriteria[index].toString() }

    var lines = matchingLines
    if (matchingLines.size == mismatchedLines.size
        && matchingLines.first()[index].toString() != bitCriteria.toString()
    ) {
        lines = mismatchedLines
    }

    return if (lines.size > 1) {
        getLifeSupportRating(lines, bitCriteria, index + 1)
    } else {
        lines.first().map { Integer.parseInt(it.toString()) }
    }
}

private fun getMostCommonBits(lines: List<String>): List<Int> {
    val totals = (0 until lines.first().length)
        .map { 0 }
        .toMutableList()
    for (line in lines) {
        for ((i, bit) in line.withIndex()) {
            when (bit) {
                '0' -> totals[i] -= 1
                '1' -> totals[i] += 1
            }
        }
    }

    return totals.map {
        if (it > 0) {
            1
        } else {
            0
        }
    }
}

private fun getLeastCommonBits(lines: List<String>): List<Int> {
    return getMostCommonBits(lines)
        .map {
            if (it == 0) {
                1
            } else {
                0
            }
        }
}

fun List<Int>.toDecimal() = Integer.parseInt(this.joinToString(separator = ""), 2)
fun List<Int>.toFlippedDecimal() = Integer.parseInt(this.joinToString(separator = "") {
    if (it == 0) {
        "1"
    } else {
        "0"
    }
}, 2)