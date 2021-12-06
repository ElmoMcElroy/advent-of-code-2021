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

        val diagnosticReport = subjectUnderTest.calculateDiagnosticReport(diagnosticReportRawData)
        assertEquals(22, diagnosticReport.gammaRateAsInt())
        assertEquals(9, diagnosticReport.epsilonRateAsInt())
        assertEquals(198, diagnosticReport.powerConsumption())
    }

    @Test
    fun testPowerConsumptionWithFile() {
        val diagnosticReportRaw = Utils.readFileAsString("/task03/input.txt")
        assertNotNull(diagnosticReportRaw)
        assertEquals(1000, diagnosticReportRaw.size)
        val diagnosticReport = subjectUnderTest.calculateDiagnosticReport(diagnosticReportRaw)
        println("DiagnosticReport gamma rate: ${diagnosticReport.gammaRateAsInt()}, epsilon rate: ${diagnosticReport.epsilonRateAsInt()}. " +
                "Multiplied: ${diagnosticReport.powerConsumption()}")
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

        val diagnosticReport = subjectUnderTest.calculateDiagnosticReport(diagnosticReportRawData)
        assertEquals(23, diagnosticReport.oxygenGeneratorRatingAsInt())
        assertEquals(10, diagnosticReport.co2ScrubberRatingAsInt())
        assertEquals(230, diagnosticReport.liveSupportRating())
    }

    @Test
    fun testLifeSupportRatingWithFile() {
        val diagnosticReportRaw = Utils.readFileAsString("/task03/input2.txt")
        assertNotNull(diagnosticReportRaw)
        assertEquals(1000, diagnosticReportRaw.size)
        val diagnosticReport = subjectUnderTest.calculateDiagnosticReport(diagnosticReportRaw)
        println("DiagnosticReport oxygen generator rating: ${diagnosticReport.oxygenGeneratorRatingAsInt()}, " +
                "CO2 scrubber rating: ${diagnosticReport.co2ScrubberRatingAsInt()}. " +
                "life support rating: ${diagnosticReport.liveSupportRating()}")
    }

}