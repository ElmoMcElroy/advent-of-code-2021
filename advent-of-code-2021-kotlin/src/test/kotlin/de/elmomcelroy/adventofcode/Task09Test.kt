package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task09Test {

    private val subjectUnderTest = LavaTubes()

    @Test
    fun testFindLowestPoints() {
        val heightMap = Utils.readRowsWithoutSeparatorAsInt("/task09/test.txt")

        val lowestPoints: List<Pair<Int, Int>> = subjectUnderTest.findLowestPoints(heightMap)
        assertEquals(4, lowestPoints.size, "Number of lowest points")
        assertEquals(Pair(0, 1), lowestPoints[0], "First lowest point")
        assertEquals(Pair(0, 9), lowestPoints[1], "Second lowest point")
        assertEquals(Pair(2, 2), lowestPoints[2], "Third lowest point")
        assertEquals(Pair(4, 6), lowestPoints[3], "Fourth lowest point")

        val riskLevelSum = subjectUnderTest.calculateRiskLevel(heightMap)
        assertEquals(15, riskLevelSum, "Sum of risk levels")

    }

    @Test
    fun testFindLowestPointsWithFile() {
        val heightMap = Utils.readRowsWithoutSeparatorAsInt("/task09/input.txt")

        val riskLevelSum = subjectUnderTest.calculateRiskLevel(heightMap)
        assertEquals(541, riskLevelSum, "Sum of risk levels")
    }

    @Test
    fun testFindBasins() {
        val heightMap = Utils.readRowsWithoutSeparatorAsInt("/task09/test.txt")

        val basins: List<List<Pair<Int, Int>>> = subjectUnderTest.findBasins(heightMap)
        assertEquals(4, basins.size, "Number of basins")
        assertEquals(3, basins[0].size, "First basin size")
        assertEquals(9, basins[1].size, "Second basin size")
        assertEquals(14, basins[2].size, "Third basin size")
        assertEquals(9, basins[3].size, "Fourth basin size")

        val calculatedBasins = subjectUnderTest.calculateBiggestBasins(heightMap)
        assertEquals(1134, calculatedBasins, "Three biggest basins multiplied")

    }

    @Test
    fun testFindBasinsWithFile() {
        val heightMap = Utils.readRowsWithoutSeparatorAsInt("/task09/input.txt")

        val calculatedBasins = subjectUnderTest.calculateBiggestBasins(heightMap)
        assertEquals(847504, calculatedBasins, "Three biggest basins multiplied")
    }


}