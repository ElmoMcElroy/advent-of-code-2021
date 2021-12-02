package de.elmomcelroy.eazyfirsttry

import java.lang.IllegalArgumentException

class Calculator {
    fun multiply(x: Int, y: Int): Int {
        return x * y
    }

    fun divide(x: Double, y: Double): Double {
        return x / y
    }

    fun parseOperation(input: String): Int {
        val (x, op, y) = input.split(" ")
        return  when (op) {
            "*" -> x.toInt() * y.toInt()
            "/" -> x.toInt() / y.toInt()
            else -> throw IllegalArgumentException("Invalid Operator")
        }
    }
}