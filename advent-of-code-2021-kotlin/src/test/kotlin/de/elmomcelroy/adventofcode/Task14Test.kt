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
        val scoreAfter4 = subjectUnderTest.calculateScore(polymerAfter4Steps)
        assertEquals(18, scoreAfter4, "Score after 4 steps")

        val polymerAfter5Steps: String = subjectUnderTest.extendPolymer(polymerRules, 5)
        assertEquals(97, polymerAfter5Steps.length, "Polymer length after 5 steps")
        val scoreAfter5 = subjectUnderTest.calculateScore(polymerAfter5Steps)
        assertEquals(33, scoreAfter5, "Score after 5 steps")

        val polymerAfter10Steps: String = subjectUnderTest.extendPolymer(polymerRules, 10)
        assertEquals(3073, polymerAfter10Steps.length, "Polymer length after 10 steps")
        val scoreAfter10 = subjectUnderTest.calculateScore(polymerAfter10Steps)
        assertEquals(1588, scoreAfter10, "Polymer score after 10 steps")
    }

    @Test
    fun testExtendedPolymerizationWithFile() {
        val polymerRules = Utils.readFileAsString("/task14/input.txt")

        val polymerAfter10Steps: String = subjectUnderTest.extendPolymer(polymerRules, 10)
        assertEquals(19457, polymerAfter10Steps.length, "Polymer length after 10 steps")
        val score = subjectUnderTest.calculateScore(polymerAfter10Steps)
        assertEquals(2360, score, "Polymer score after 10 steps")
    }

    @Test
    fun testExtendedPolymerizationRecursive() {
        val polymerRules = Utils.readFileAsString("/task14/test.txt")

        val charsAfter4Steps = subjectUnderTest.countCharacters(polymerRules, 4)
        val scoreAfter4 = subjectUnderTest.calculateScore(charsAfter4Steps)
        assertEquals(18, scoreAfter4, "Score after 4 steps")

        val charsAfter5Steps = subjectUnderTest.countCharacters(polymerRules, 5)
        val scoreAfter5 = subjectUnderTest.calculateScore(charsAfter5Steps)
        assertEquals(33, scoreAfter5, "Score after 5 steps")

        val charsAfter10Steps = subjectUnderTest.countCharacters(polymerRules, 10)
        val scoreAfter10 = subjectUnderTest.calculateScore(charsAfter10Steps)
        assertEquals(1588, scoreAfter10, "Score after 10 steps")

        val charsAfter40Steps = subjectUnderTest.countCharacters(polymerRules, 40)
        val scoreAfter40 = subjectUnderTest.calculateScore(charsAfter40Steps)
        assertEquals(2188189693529, scoreAfter40, "Score after 10 steps")
    }

    @Test
    fun testExtendedPolymerizationRecursiveWithFile() {
        val polymerRules = Utils.readFileAsString("/task14/input.txt")

        val charsAfter10Steps = subjectUnderTest.countCharacters(polymerRules, 10)
        val score = subjectUnderTest.calculateScore(charsAfter10Steps)
        assertEquals(2360, score, "Polymer score after 10 steps")

        val charsAfter40Steps = subjectUnderTest.countCharacters(polymerRules, 40)
        val scoreAfter40 = subjectUnderTest.calculateScore(charsAfter40Steps)
        assertEquals(2967977072188, scoreAfter40, "Score after 10 steps")
    }

}