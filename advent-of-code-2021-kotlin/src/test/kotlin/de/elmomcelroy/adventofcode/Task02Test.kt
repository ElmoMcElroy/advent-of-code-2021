package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class Task02Test {

    private val subjectUnderTest: Submarine = Submarine()


    @Test
    fun testMeasureDepthIncrease() {
        val position1 = subjectUnderTest.move("FORWARD 5")
        assertEquals(5, position1.horizontal)
        assertEquals(0, position1.depth)
        assertEquals(0, position1.aim)
        val position2 = subjectUnderTest.move("down 5")
        assertEquals(5, position2.horizontal)
        assertEquals(0, position2.depth)
        assertEquals(5, position2.aim)
        val position3 = subjectUnderTest.move("forward 8")
        assertEquals(13, position3.horizontal)
        assertEquals(40, position3.depth)
        assertEquals(5, position3.aim)
        val position4 = subjectUnderTest.move("up 3")
        assertEquals(13, position4.horizontal)
        assertEquals(40, position4.depth)
        assertEquals(2, position4.aim)
        val position5 = subjectUnderTest.move("down 8")
        assertEquals(13, position5.horizontal)
        assertEquals(40, position5.depth)
        assertEquals(10, position5.aim)
        val position6 = subjectUnderTest.move("forward 2")
        assertEquals(15, position6.horizontal)
        assertEquals(60, position6.depth)
        assertEquals(10, position6.aim)
        assertEquals(900, subjectUnderTest.position.horizontal * subjectUnderTest.position.depth)
    }


    @Test
    fun testSubmarineMovementWithFile() {
        val movements = Utils.readFileAsString("/task02/input.txt")
        assertNotNull(movements)
        assertEquals(1000, movements.size)
        val position = subjectUnderTest.moveWithPlannedInput(movements)
        println("Position is: ${position.horizontal}, ${position.depth}. Multiplied: ${position.horizontal * position.depth}")
    }

}