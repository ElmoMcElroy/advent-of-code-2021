package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task03Test {

    private val subjectUnderTest: DiagnosticReport = DiagnosticReport()


    @Test
    fun testPowerConsumption() {
        val diagnosticReportRawData = listOf("00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010")

        val powerConsumption = subjectUnderTest.calculatePowerConsumption(diagnosticReportRawData)
        assertEquals(22, subjectUnderTest.gammaRateAsInt())
        assertEquals(9, subjectUnderTest.epsilonRateAsInt())
        assertEquals(198, powerConsumption)
    }

    @Test
    fun testPowerConsumptionWithFile() {
        val diagnosticReportRaw = Utils.readFileAsString("/task03/input.txt")
        assertNotNull(diagnosticReportRaw)
        assertEquals(1000, diagnosticReportRaw.size)
        val powerConsumption = subjectUnderTest.calculatePowerConsumption(diagnosticReportRaw)
        println("DiagnosticReport gamma rate: ${subjectUnderTest.gammaRateAsInt()}, epsilon rate: ${subjectUnderTest.epsilonRateAsInt()}. " +
                "Multiplied: $powerConsumption")
    }

    @Test
    fun testLifeSupportRating() {
        val diagnosticReportRawData = listOf("00100",
                "11110",
                "10110",
                "10111",
                "10101",
                "01111",
                "00111",
                "11100",
                "10000",
                "11001",
                "00010",
                "01010")

        val lifeSupportRating = subjectUnderTest.calculateLifeSupportRating(diagnosticReportRawData)
        assertEquals(23, subjectUnderTest.oxygenGeneratorRatingAsInt(), "oxygenGeneratorRating")
        assertEquals(10, subjectUnderTest.co2ScrubberRatingAsInt(), "co2ScrubberRating")
        assertEquals(230, lifeSupportRating, "lifeSupportRating")
    }

    @Test
    fun testLifeSupportRatingWithFile() {
        val diagnosticReportRaw = Utils.readFileAsString("/task03/input2.txt")
        assertNotNull(diagnosticReportRaw)
        assertEquals(1000, diagnosticReportRaw.size)
        val lifeSupportRating = subjectUnderTest.calculateLifeSupportRating(diagnosticReportRaw)
        println("DiagnosticReport oxygen generator rating: ${subjectUnderTest.oxygenGeneratorRatingAsInt()}, " +
                "CO2 scrubber rating: ${subjectUnderTest.co2ScrubberRatingAsInt()}. " +
                "life support rating: $lifeSupportRating")
        assertEquals(3969126, lifeSupportRating)
    }

}