package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task08Test {

    private val subjectUnderTest = SevenSegmentDisplay()

    @Test
    fun testSimpleOutputSegments() {
        val recordedSegments = Utils.readFileAsString("/task08/test.txt")

        val simpleOutputSegments = subjectUnderTest.simpleOutputDigits(recordedSegments)
        assertEquals(26, simpleOutputSegments, "Count of simple output digits")
    }

    @Test
    fun testCheapestFuelPositionWithFile() {
        val recordedSegments = Utils.readFileAsString("/task08/input.txt")

        val simpleOutputSegments = subjectUnderTest.simpleOutputDigits(recordedSegments)
        assertEquals(416, simpleOutputSegments, "Count of simple output digits")
    }

    @Test
    fun testDetermineDigits() {
        val recordedSegments = Pair(listOf("acedgfb", "cdfbe", "gcdfa", "fbcad", "dab", "cefabd", "cdfgeb", "eafb", "cagedb", "ab"), listOf("cdfeb", "fcadb", "cdfeb", "cdbaf"))

        val digitsMap = subjectUnderTest.determineDigits(recordedSegments)
        assertEquals(0, digitsMap["abcdeg"], "Segements for 0")
        assertEquals(1, digitsMap["ab"], "Segements for 1")
        assertEquals(2, digitsMap["acdfg"], "Segements for 2")
        assertEquals(3, digitsMap["abcdf"], "Segements for 3")
        assertEquals(4, digitsMap["abef"], "Segements for 4")
        assertEquals(5, digitsMap["bcdef"], "Segements for 5")
        assertEquals(6, digitsMap["bcdefg"], "Segements for 6")
        assertEquals(7, digitsMap["abd"], "Segements for 7")
        assertEquals(8, digitsMap["abcdefg"], "Segements for 8")
        assertEquals(9, digitsMap["abcdef"], "Segements for 9")
    }

    @Test
    fun testDetermineOutput() {
        val recordedSegments = Utils.readFileAsString("/task08/test.txt")

        val decodedOutput = subjectUnderTest.decodeOutput(recordedSegments)
        assertEquals(8394, decodedOutput[0], "Decoded output for position 0")
        assertEquals(9781, decodedOutput[1], "Decoded output for position 1")
        assertEquals(1197, decodedOutput[2], "Decoded output for position 2")
        assertEquals(9361, decodedOutput[3], "Decoded output for position 3")
        assertEquals(4873, decodedOutput[4], "Decoded output for position 4")
        assertEquals(8418, decodedOutput[5], "Decoded output for position 5")
        assertEquals(4548, decodedOutput[6], "Decoded output for position 6")
        assertEquals(1625, decodedOutput[7], "Decoded output for position 7")
        assertEquals(8717, decodedOutput[8], "Decoded output for position 8")
        assertEquals(4315, decodedOutput[9], "Decoded output for position 9")

        val sumDecodedOutput = subjectUnderTest.sumDecodeOutput(recordedSegments)
        assertEquals(61229, sumDecodedOutput, "Sum of decoded output")
    }

    @Test
    fun testDetermineOutputWithFile() {
        val recordedSegments = Utils.readFileAsString("/task08/input.txt")

        val sumDecodedOutput = subjectUnderTest.sumDecodeOutput(recordedSegments)
        assertEquals(1043697, sumDecodedOutput, "Sum of decoded output")
    }

}