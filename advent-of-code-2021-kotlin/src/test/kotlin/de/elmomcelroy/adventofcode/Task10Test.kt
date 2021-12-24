package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task10Test {

    private val subjectUnderTest = SyntaxScoring()

    @Test
    fun testCalculateSyntaxErrorScore() {
        val lines = Utils.readFileAsString("/task10/test.txt")

        val syntaxErrorScore = subjectUnderTest.calculateSyntaxErrorScore(lines)
        assertEquals(26397, syntaxErrorScore, "Syntax Error Score")

    }

    @Test
    fun testCalculateSyntaxErrorScoreWithFile() {
        val lines = Utils.readFileAsString("/task10/input.txt")

        val syntaxErrorScore = subjectUnderTest.calculateSyntaxErrorScore(lines)
        assertEquals(344193, syntaxErrorScore, "Syntax Error Score")
    }

    @Test
    fun testCalculateIncompleteScore() {
        val lines = Utils.readFileAsString("/task10/test.txt")

        val incompleteScore = subjectUnderTest.calculateIncompleteScore(lines)
        assertEquals(288957, incompleteScore, "Syntax Error Score")

    }

    @Test
    fun testCalculateIncompleteScoreWithFile() {
        val lines = Utils.readFileAsString("/task10/input.txt")

        val incompleteScore = subjectUnderTest.calculateIncompleteScore(lines)
        assertEquals(3241238967, incompleteScore, "Syntax Error Score")
    }

}