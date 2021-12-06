package de.elmomcelroy.adventofcode

class Position {

    var horizontal: Int = 0
    var depth: Int = 0
    var aim: Int = 0

    fun increaseHorizontal(amount: Int): Int {
        horizontal += amount
        return horizontal
    }

    fun increaseDepth(amount: Int): Int {
        depth += amount
        return depth
    }

    fun decreaseDepth(amount: Int): Int {
        depth -= amount
        return depth
    }

    fun increaseAim(amount: Int): Int {
        aim += amount
        return aim
    }

    fun decreaseAim(amount: Int): Int {
        aim -= amount
        return aim
    }


}