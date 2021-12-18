package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task07Test {

    private val subjectUnderTest = Crabs()

    @Test
    fun testCheapestFuelPosition() {

        val crabHorizontalPositions = listOf(16,1,2,0,4,2,7,1,2,14)

        val fuelPerPosition = subjectUnderTest.determineFuelConsumePerPosition(ArrayList(crabHorizontalPositions))
        assertEquals(37, fuelPerPosition[2], "Fuel cost for Position 2")
        assertEquals(41, fuelPerPosition[1], "Fuel cost for cheapest Position 1")
        assertEquals(39, fuelPerPosition[3], "Fuel cost for cheapest Position 3")
        assertEquals(71, fuelPerPosition[10], "Fuel cost for cheapest Position 10")

        val cheapestFuelPosition = subjectUnderTest.determineCheapestPosition(ArrayList(crabHorizontalPositions))
        assertEquals(37, cheapestFuelPosition, "Fuel for the cheapest position")
    }

    @Test
    fun testCheapestFuelPositionWithFile() {
        val crabHorizontalPositions = Utils.readRowsAsInt("/task07/input.txt").first()
        assertEquals(1000, crabHorizontalPositions.size)

        val cheapestPositionFuel = subjectUnderTest.determineCheapestPosition(ArrayList(crabHorizontalPositions))
        assertEquals(347509, cheapestPositionFuel, "Fuel for the cheapest position")
    }

    @Test
    fun testCheapestIncreasingFuelPosition() {

        val crabHorizontalPositions = listOf(16,1,2,0,4,2,7,1,2,14)

        val fuelPerPosition = subjectUnderTest.determineIncreasingFuelConsumePerPosition(ArrayList(crabHorizontalPositions))
        assertEquals(168, fuelPerPosition[5], "Fuel cost for cheapest Position 5")
        assertEquals(206, fuelPerPosition[2], "Fuel cost for Position 2")

        val cheapestFuelPosition = subjectUnderTest.determineCheapestPositionIncreasing(ArrayList(crabHorizontalPositions))
        assertEquals(168, cheapestFuelPosition, "Fuel for the cheapest position")
    }

    @Test
    fun testCheapestIncreasingFuelPositionWithFile() {
        val crabHorizontalPositions = Utils.readRowsAsInt("/task07/input.txt").first()
        assertEquals(1000, crabHorizontalPositions.size)

        val cheapestPositionFuel = subjectUnderTest.determineCheapestPositionIncreasing(ArrayList(crabHorizontalPositions))
        assertEquals(98257206, cheapestPositionFuel, "Fuel for the cheapest position")
    }

}