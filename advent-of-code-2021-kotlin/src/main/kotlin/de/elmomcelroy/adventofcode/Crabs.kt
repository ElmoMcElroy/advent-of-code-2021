package de.elmomcelroy.adventofcode

import kotlin.math.abs

class Crabs {

    fun determineCheapestPositionIncreasing(crabHorizontalPositions: ArrayList<Int>): Int {
        val fuelConsumePerPosition = determineIncreasingFuelConsumePerPosition(crabHorizontalPositions)

        return fuelConsumePerPosition.minOf { entry: Map.Entry<Int, Int> -> entry.value }
    }

    fun determineIncreasingFuelConsumePerPosition(crabHorizontalPositions: ArrayList<Int>): HashMap<Int, Int> {
        val fuelPerPosition = HashMap<Int, Int>()
        val minPosition = crabHorizontalPositions.minOrNull()
        val maxPosition = crabHorizontalPositions.maxOrNull()

        for (position in minPosition!! until  maxPosition!! + 1 step 1) {
            val fuel = calculateExponentialFuelOnPosition(position, crabHorizontalPositions)
//            println("Fuel on postion $position: $fuel")
            fuelPerPosition[position] = fuel
        }

        return fuelPerPosition
    }

    private fun calculateExponentialFuelOnPosition(position: Int, crabHorizontalPositions: ArrayList<Int>): Int {
        return crabHorizontalPositions
            .map { crabPosition -> abs(position - crabPosition) }
            .sumOf { distance -> calcIncreasingFuel(distance) }
    }

   private fun calcIncreasingFuel(distance: Int): Int {
       var fuel = 0
       for (distancePos in 1 until  distance + 1 step 1) {
           fuel += distancePos
       }
       println("Distance: $distance Fuel: $fuel")
       return fuel
   }

    // Part 1
    fun determineCheapestPosition(crabHorizontalPositions: ArrayList<Int>): Int {
        val fuelConsumePerPosition = determineFuelConsumePerPosition(crabHorizontalPositions)

        return fuelConsumePerPosition.minOf { entry: Map.Entry<Int, Int> -> entry.value }
    }

    fun determineFuelConsumePerPosition(crabHorizontalPositions: ArrayList<Int>): HashMap<Int, Int> {
        val fuelPerPosition = HashMap<Int, Int>()
        val minPosition = crabHorizontalPositions.minOrNull()
        val maxPosition = crabHorizontalPositions.maxOrNull()

        for (position in minPosition!! until  maxPosition!! + 1 step 1) {
            val fuel = calculateFuelOnPosition(position, crabHorizontalPositions)
//            println("Fuel on postion $position: $fuel")
            fuelPerPosition[position] = fuel
        }

        return fuelPerPosition
    }

    private fun calculateFuelOnPosition(position: Int, crabHorizontalPositions: ArrayList<Int>): Int {
        return crabHorizontalPositions.sumOf { crabPosition -> abs(position - crabPosition) }
    }

}