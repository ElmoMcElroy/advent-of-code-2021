package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task11Test {

    private val subjectUnderTest = DumboOctopus()

    @Test
    fun testSimpleFlashes() {
        val energyLevel = Utils.readRowsWithoutSeparatorAsInt("/task11/test_simple.txt")

        val flashes = subjectUnderTest.simulateSteps(energyLevel, 2)
        assertEquals(9, flashes, "Flashes after 2 steps")

    }

    @Test
    fun testFlashes() {
        val energyLevel = Utils.readRowsWithoutSeparatorAsInt("/task11/test.txt")

        val flashes10 = subjectUnderTest.simulateSteps(energyLevel, 10)
        assertEquals(204, flashes10, "Flashes after 10 steps")

        val flashes = subjectUnderTest.simulateSteps(energyLevel, 100)
        assertEquals(1656, flashes, "Flashes after 100 steps")
    }

    @Test
    fun testFlashesWithFile() {
        val energyLevel = Utils.readRowsWithoutSeparatorAsInt("/task11/input.txt")

        val syntaxErrorScore = subjectUnderTest.simulateSteps(energyLevel, 100)
        assertEquals(1673, syntaxErrorScore, "Flashes after 100 steps")
    }

    @Test
    fun testFlashesSimultaneously() {
        val energyLevel = Utils.readRowsWithoutSeparatorAsInt("/task11/test.txt")

        val flashes = subjectUnderTest.simulateSimultaneouslyFlash(energyLevel)
        assertEquals(195, flashes, "Flashes simultaneously")
    }

    @Test
    fun testFlashesSimultaneouslyWithFile() {
        val energyLevel = Utils.readRowsWithoutSeparatorAsInt("/task11/input.txt")

        val syntaxErrorScore = subjectUnderTest.simulateSimultaneouslyFlash(energyLevel)
        assertEquals(279, syntaxErrorScore, "Flashes simultaneously")
    }

}