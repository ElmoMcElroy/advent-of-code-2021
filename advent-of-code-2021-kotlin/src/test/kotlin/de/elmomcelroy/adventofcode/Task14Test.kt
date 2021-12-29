package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task14Test {

    private val subjectUnderTest = ExtendedPolymerization()

    @Test
    fun testExtendedPolymerization() {
        val polymerRules = Utils.readFileAsString("/task14/test.txt")

        val polymerAfter4Steps: String = subjectUnderTest.extendPolymer(polymerRules, 4)
        assertEquals("NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB", polymerAfter4Steps, "Polymer after 4 steps")
        val polymerAfter5Steps: String = subjectUnderTest.extendPolymer(polymerRules, 5)
        assertEquals(97, polymerAfter5Steps.length, "Polymer length after 5 steps")
        val polymerAfter10Steps: String = subjectUnderTest.extendPolymer(polymerRules, 10)
        assertEquals(3073, polymerAfter10Steps.length, "Polymer length after 10 steps")
        val score = subjectUnderTest.calculateScore(polymerAfter10Steps)
        assertEquals(1588, score, "Polymer score after 10 steps")

        val polymerAfter40Steps: String = subjectUnderTest.extendPolymer(polymerRules, 40)
//        assertEquals(3073, polymerAfter40Steps.length, "Polymer length after 40 steps")
//        val scoreAfter40 = subjectUnderTest.calculateScore(polymerAfter40Steps)
//        assertEquals(2188189693529, scoreAfter40, "Polymer score after 10 steps")
    }

    @Test
    fun testExtendedPolymerizationWithFile() {
        val polymerRules = Utils.readFileAsString("/task14/input.txt")

        val polymerAfter10Steps: String = subjectUnderTest.extendPolymer(polymerRules, 10)
        assertEquals(19457, polymerAfter10Steps.length, "Polymer length after 10 steps")
        val score = subjectUnderTest.calculateScore(polymerAfter10Steps)
        assertEquals(2360, score, "Polymer score after 10 steps")
    }

}