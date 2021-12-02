package de.elmomcelroy.eazyfirsttry

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CalculatorTest {

    private val subjectUnderTest: Calculator = Calculator()

    @Test
    fun testMultiply() {
        assertEquals(4, subjectUnderTest.multiply(2, 2))
        assertEquals(3, subjectUnderTest.multiply(3, 1))
        assertEquals(10, subjectUnderTest.parseOperation("5 * 2"))
        assertThrows<IllegalArgumentException> {
            subjectUnderTest.parseOperation("5 5 5")
        }
    }

    @Test
    fun testDivide() {
        assertEquals(2.0, subjectUnderTest.divide(10.0, 5.0))
        assertEquals(1.5, subjectUnderTest.divide(7.5, 5.0))
        assertEquals(5, subjectUnderTest.parseOperation("10 / 2"))
        assertThrows<IllegalArgumentException> {
            subjectUnderTest.parseOperation("5 5 5")
        }
    }
}