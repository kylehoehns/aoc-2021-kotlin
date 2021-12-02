import java.io.File

fun main() {

    val moveCommands = File("src/main/kotlin", "Day02.txt")
        .readLines()
        .map { toMoveCommand(it) }

    val finalPositionOne = performMovesPartOne(moveCommands)
    println("Part One: ${finalPositionOne.horizontal * finalPositionOne.depth}")

    val finalPositionTwo = performMovesPartTwo(moveCommands)
    println("Part Two: ${finalPositionTwo.horizontal * finalPositionTwo.depth}")
}

fun performMovesPartOne(moveCommands: List<MoveCommand>): FinalPosition {
    var horizontal = 0
    var depth = 0
    moveCommands.forEach {
        when (it.command) {
            "up" -> depth -= it.units
            "down" -> depth += it.units
            "forward" -> horizontal += it.units
        }
    }
    return FinalPosition(horizontal, depth)
}

fun performMovesPartTwo(moveCommands: List<MoveCommand>): FinalPosition {
    var horizontal = 0
    var depth = 0
    var aim = 0
    moveCommands.forEach {
        when (it.command) {
            "up" -> aim -= it.units
            "down" -> aim += it.units
            "forward" -> {
                horizontal += it.units
                depth += aim * it.units
            }
        }
    }
    return FinalPosition(horizontal, depth)
}

fun toMoveCommand(line: String): MoveCommand {
    val (command, units) = line.split(" ")
    return MoveCommand(command, units.toInt())
}

data class MoveCommand(val command: String, val units: Int)

data class FinalPosition(val horizontal: Int, val depth: Int)

