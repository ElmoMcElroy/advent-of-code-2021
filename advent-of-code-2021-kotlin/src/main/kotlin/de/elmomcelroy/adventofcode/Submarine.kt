package de.elmomcelroy.adventofcode

import java.lang.IllegalArgumentException

class Submarine {

    val position: Position = Position()
    val diagnosticReport: DiagnosticReport = DiagnosticReport()

    fun move(order: String): Position {
        val (op, amount) = order.split(" ")

        when (op.lowercase()) {
            "forward" -> moveForward(amount.toInt())
            "down" -> position.increaseAim(amount.toInt())
            "up" -> position.decreaseAim(amount.toInt())
            else -> throw IllegalArgumentException("Invalid Operator")
        }
        println("Position(horizontal, depth): ${position.horizontal}, ${position.depth}")
        return position
    }

    private fun moveForward(amount: Int): Position {
        position.increaseHorizontal(amount)
        position.increaseDepth(position.aim * amount)
        return position
    }

    fun moveWithPlannedInput(movements: List<String>): Position {
        movements.forEach { movement -> move(movement) }
        println("Final position reached")
        return position
    }

}