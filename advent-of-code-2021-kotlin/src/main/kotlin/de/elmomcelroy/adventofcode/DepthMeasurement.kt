package de.elmomcelroy.adventofcode

class DepthMeasurement {

    fun measureDepthIncrease(measurements: List<Int>): Int {
        val listIterator = measurements.listIterator()
        var counter = 0
        var previousMeasure = listIterator.next()

        while (listIterator.hasNext()) {
            val currentMeasure = listIterator.next()
            if (previousMeasure < currentMeasure) counter++
            previousMeasure = currentMeasure
        }

        println("Found $counter depth increases")
        println()

        return counter
    }

    fun measureDepthIncreaseV2(measurements: List<Int>): Int {
        var counter = 0
        var previousMeasure = measurements[0]

        for (i in 0 until  measurements.size - 1 step 1) {
            val currentMeasure =  measurements[i+1]
            if (previousMeasure < currentMeasure) counter++
            previousMeasure = currentMeasure
        }

        println("Found $counter depth increases")
        println()

        return counter
    }

    fun measureTripleDepthIncrease(measurements: List<Int>): Int {
        var counter = 0
        if (measurements.size < 4) {
            error("Input list is too small. Needs at least 4 items to compare")
        }
        var previousMeasureSum = sumTriple(measurements, 0)
        for (i in 0 until  measurements.size - 3 step 1) {
            if (i + 3 > measurements.size) break

            val currentMeasureSum = sumTriple(measurements, i+1)
            if (previousMeasureSum < currentMeasureSum) counter++
            previousMeasureSum = currentMeasureSum
        }

        println("Found $counter depth increases")
        println()

        return counter
    }

    private fun sumTriple(input: List<Int>, index: Int): Int = input[index] + input[index + 1] + input[index + 2]

}