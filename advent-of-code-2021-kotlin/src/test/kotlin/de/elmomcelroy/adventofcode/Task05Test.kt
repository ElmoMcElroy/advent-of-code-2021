package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task05Test {

    @Test
    fun testFindHydrothermalVents() {
        val subjectUnderTest = HydrothermalVenture(10, 10)
        val hydrothermalVentsInput = Utils.readFileAsString("/task05/test.txt")
        assertNotNull(hydrothermalVentsInput)
        assertEquals(10, hydrothermalVentsInput.size)

        val sumOfOverlap = subjectUnderTest.findHydrothermalVents(hydrothermalVentsInput, false)
        assertEquals(10, subjectUnderTest.vectors.size, "Number of Vectors")
        assertEquals(0, subjectUnderTest.vectors.first().startX, "Vector 1 startX")
        assertEquals(9, subjectUnderTest.vectors.first().startY, "Vector 1 startY")
        assertEquals(5, subjectUnderTest.vectors.first().endX, "Vector 1 endX")
        assertEquals(9, subjectUnderTest.vectors.first().endY, "Vector 1 endY")

        assertEquals(5, sumOfOverlap, "Sum of overlap")
    }

    @Test
    fun testFindHydrothermalVentsWithFile() {
        val subjectUnderTest = HydrothermalVenture(1000, 1000)
        val hydrothermalVentsInput = Utils.readFileAsString("/task05/input.txt")
        assertNotNull(hydrothermalVentsInput)
        assertEquals(500, hydrothermalVentsInput.size)

        val sumOfOverlap = subjectUnderTest.findHydrothermalVents(hydrothermalVentsInput, false)
        assertEquals(500, subjectUnderTest.vectors.size, "Number of Vectors")
        println("sum of overlap: $sumOfOverlap")
    }

    @Test
    fun testFindHydrothermalVentsWithDiagonal() {
        val subjectUnderTest = HydrothermalVenture(10, 10)
        val hydrothermalVentsInput = Utils.readFileAsString("/task05/test.txt")
        assertNotNull(hydrothermalVentsInput)
        assertEquals(10, hydrothermalVentsInput.size)

        val sumOfOverlap = subjectUnderTest.findHydrothermalVents(hydrothermalVentsInput, true)
        assertEquals(10, subjectUnderTest.vectors.size, "Number of Vectors")
        assertEquals(0, subjectUnderTest.vectors.first().startX, "Vector 1 startX")
        assertEquals(9, subjectUnderTest.vectors.first().startY, "Vector 1 startY")
        assertEquals(5, subjectUnderTest.vectors.first().endX, "Vector 1 endX")
        assertEquals(9, subjectUnderTest.vectors.first().endY, "Vector 1 endY")

        assertEquals(12, sumOfOverlap, "Sum of overlap")
    }

    @Test
    fun testFindHydrothermalVentsWithDiagonalWithFile() {
        val subjectUnderTest = HydrothermalVenture(1000, 1000)
        val hydrothermalVentsInput = Utils.readFileAsString("/task05/input.txt")
        assertNotNull(hydrothermalVentsInput)
        assertEquals(500, hydrothermalVentsInput.size)

        val sumOfOverlap = subjectUnderTest.findHydrothermalVents(hydrothermalVentsInput, true)
        assertEquals(500, subjectUnderTest.vectors.size, "Number of Vectors")
        println("sum of overlap: $sumOfOverlap")
    }

}