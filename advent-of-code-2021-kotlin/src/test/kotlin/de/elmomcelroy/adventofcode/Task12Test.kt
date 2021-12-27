package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task12Test {

    private val subjectUnderTest = PassagePathing()

    @Test
    fun testFindPaths_simple() {
        val paths = Utils.readFileAsString("/task12/test_simple.txt")

        val numPossiblePaths = subjectUnderTest.findPaths(paths)
        assertEquals(10, numPossiblePaths, "Number of possible paths")
        val numPossiblePaths2 = subjectUnderTest.findPathsWithDoubleVisit(paths)
        assertEquals(36, numPossiblePaths2, "Number of possible paths for small double visit")
    }

    @Test
    fun testFindPaths() {
        val paths = Utils.readFileAsString("/task12/test.txt")

        val numPossiblePaths = subjectUnderTest.findPaths(paths)
        assertEquals(19, numPossiblePaths, "Number of possible paths")
        val numPossiblePaths2 = subjectUnderTest.findPathsWithDoubleVisit(paths)
        assertEquals(103, numPossiblePaths2, "Number of possible paths for small double visit")
    }

    @Test
    fun testFindPaths_complex() {
        val paths = Utils.readFileAsString("/task12/test_complex.txt")

        val numPossiblePaths = subjectUnderTest.findPaths(paths)
        assertEquals(226, numPossiblePaths, "Number of possible paths")
        val numPossiblePaths2 = subjectUnderTest.findPathsWithDoubleVisit(paths)
        assertEquals(3509, numPossiblePaths2, "Number of possible paths for small double visit")
    }

    @Test
    fun testFlashesWithFile() {
        val paths = Utils.readFileAsString("/task12/input.txt")

        val numPossiblePaths = subjectUnderTest.findPaths(paths)
        assertEquals(5076, numPossiblePaths, "Number of possible paths")
        val numPossiblePaths2 = subjectUnderTest.findPathsWithDoubleVisit(paths)
        assertEquals(145643, numPossiblePaths2, "Number of possible paths for small double visit")
    }

}