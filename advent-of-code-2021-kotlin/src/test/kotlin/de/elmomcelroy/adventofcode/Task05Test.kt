package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task05Test {

    private val subjectUnderTest: HydrothermalVenture = HydrothermalVenture()


    @Test
    fun testsingleBingoGame() {
        val bingoRawInput = Utils.readFileAsString("/task05/test.txt")
        assertNotNull(bingoRawInput)
        assertEquals(10, bingoRawInput.size)

//        val sumOfUnmarked = subjectUnderTest.play(bingoRawInput, false)
//        assertEquals(listOf(7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1), subjectUnderTest.draws)
//        assertEquals(1, subjectUnderTest.boards.size)
//        assertEquals(24, subjectUnderTest.winningNumber)
//        assertEquals(188, sumOfUnmarked)
//        assertEquals(4512, subjectUnderTest.winningNumber * sumOfUnmarked)
    }

    @Test
    fun testBingoGameWithFile() {
        val bingoRawInput = Utils.readFileAsString("/task05/input.txt")
        assertNotNull(bingoRawInput)
        assertEquals(500, bingoRawInput.size)

//        val sumOfUnmarked = subjectUnderTest.play(bingoRawInput, false)
//        assertEquals(listOf(27,14,70,7,85,66,65,57,68,23,33,78,4,84,25,18,43,71,76,61,34,82,93,74,26,15,83,64,2,35,19,97,32,47,6,51,99,20,77,75,56,73,80,86,55,36,13,95,52,63,79,72,9,10,16,8,69,11,50,54,81,22,45,1,12,88,44,17,62,0,96,94,31,90,39,92,37,40,5,98,24,38,46,21,30,49,41,87,91,60,48,29,59,89,3,42,58,53,67,28), subjectUnderTest.draws)
//        println("Winning number: ${subjectUnderTest.winningNumber}, sum of unmarked fields: $sumOfUnmarked. " +
//                "Multiplied: ${subjectUnderTest.winningNumber * sumOfUnmarked}")
    }

}