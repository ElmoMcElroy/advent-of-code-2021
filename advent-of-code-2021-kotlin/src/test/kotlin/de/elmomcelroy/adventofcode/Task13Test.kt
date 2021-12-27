package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task13Test {

    private val subjectUnderTest = TransparentOrigami()

    @Test
    fun testFold() {
        val foldInstructions = Utils.readFileAsString("/task13/test.txt")

        val dotsAfterFirstFold = subjectUnderTest.firstFold(foldInstructions)
        assertEquals(17, dotsAfterFirstFold, "Dots after first fold")

        val foldedPaper = subjectUnderTest.foldByInstructions(foldInstructions)
        val paper: Array<BooleanArray> = Array(foldedPaper[0].size) { BooleanArray(foldedPaper.size) }

        for (x in foldedPaper.indices step 1) {
            for (y in foldedPaper[x].indices step 1) {
                paper[y][x] = foldedPaper[x][y]
            }
        }
        subjectUnderTest.printPaper(paper)
    }

    @Test
    fun testFoldWithFile() {
        val foldInstructions = Utils.readFileAsString("/task13/input.txt")

        val dotsAfterFirstFold = subjectUnderTest.firstFold(foldInstructions)
        assertEquals(753, dotsAfterFirstFold, "Dots after first fold")

        val foldedPaper = subjectUnderTest.foldByInstructions(foldInstructions)
        val paper: Array<BooleanArray> = Array(foldedPaper[0].size) { BooleanArray(foldedPaper.size) }

        for (x in foldedPaper.indices step 1) {
            for (y in foldedPaper[x].indices step 1) {
                paper[y][x] = foldedPaper[x][y]
            }
        }
        subjectUnderTest.printPaper(paper)
    }

}