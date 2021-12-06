package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class Task01Test {

    private val subjectUnderTest: DepthMeasurement = DepthMeasurement()


    @Test
    fun testMeasureDepthIncrease() {
        val result1 = subjectUnderTest.measureDepthIncrease(listOf<Int>(100, 200, 300, 400, 500))
        assertEquals(4, result1)
        val result2 = subjectUnderTest.measureDepthIncrease(listOf<Int>(100, 99, 300, 400, 350))
        assertEquals(2, result2)
        val result3 = subjectUnderTest.measureDepthIncrease(listOf<Int>(100, 99, 98, 97, 50))
        assertEquals(0, result3)
    }

    @Test
    fun testMeasureDepthIncreaseV2() {
        val result1 = subjectUnderTest.measureDepthIncreaseV2(listOf<Int>(100, 200, 300, 400, 500))
        assertEquals(4, result1)
        val result2 = subjectUnderTest.measureDepthIncreaseV2(listOf<Int>(100, 99, 300, 400, 350))
        assertEquals(2, result2)
        val result3 = subjectUnderTest.measureDepthIncreaseV2(listOf<Int>(100, 99, 98, 97, 50))
        assertEquals(0, result3)
    }

    @Test
    fun testTripleMeasureDepthIncrease() {
        val result1 = subjectUnderTest.measureTripleDepthIncrease(listOf<Int>(100, 110, 120, 130, 140, 150, 160, 170, 180))
        assertEquals(6, result1)
        val result2 = subjectUnderTest.measureTripleDepthIncrease(listOf<Int>(100, 110, 100, 140, 100, 90, 160, 170, 70))
        assertEquals(3, result2)
        val result3 = subjectUnderTest.measureTripleDepthIncrease(listOf<Int>(100, 90, 80, 70, 60, 50, 40, 30, 20))
        assertEquals(0, result3)
        assertThrows<IllegalStateException> { subjectUnderTest.measureTripleDepthIncrease(listOf(100, 200, 300)) }
    }

    @Test
    fun testMeasureDepthIncreaseWithFile() {
        val measurements = Utils.readFileAsInt("/task01/input.txt")
        assertNotNull(measurements)
        assertEquals(2000, measurements.size)
        subjectUnderTest.measureDepthIncreaseV2(measurements)
    }

    @Test
    fun testTripleMeasureDepthIncreaseWithFile() {
        val measurements = Utils.readFileAsInt("/task01/input.txt")
        assertNotNull(measurements)
        assertEquals(2000, measurements.size)
        subjectUnderTest.measureTripleDepthIncrease(measurements)
    }

}