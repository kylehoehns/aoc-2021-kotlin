import java.io.File

fun main() {

    val depths = File("src/main/kotlin", "Day01.txt")
        .readLines()
        .map { it.toInt() }

    val numberOfIncreases = determineNumberOfIncreases(depths)
    println("Part One: $numberOfIncreases")

    val slidingNumberOfIncreases = determineNumberOfIncreases(depths, 3)
    println("Part Two: $slidingNumberOfIncreases")
}

fun determineNumberOfIncreases(depths: List<Int>, windowSize: Int = 1): Int {
    var numberOfIncreases = 0
    var previous = Integer.MAX_VALUE
    for (window in depths.windowed(windowSize)) {
        val sum = window.sum()
        if (sum > previous) {
            numberOfIncreases++
        }
        previous = sum
    }
    return numberOfIncreases
}

