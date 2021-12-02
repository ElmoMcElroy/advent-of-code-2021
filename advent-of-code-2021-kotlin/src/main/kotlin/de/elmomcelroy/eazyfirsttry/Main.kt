package de.elmomcelroy.eazyfirsttry

fun main(args: Array<String>) {
    println("Hello world!")
    val avg = findAverage(args)
    println(avg)
}

fun findAverage(input: Array<String>): Double {
    var result = 0.0
    for (s in input) {
        result += s.toDouble()
    }
    return result
}

