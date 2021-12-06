package de.elmomcelroy.adventofcode

class DiagnosticReport {
    var gammaRate: String = ""
    var epsilonRate: String = ""
    var oxygenGeneratorRating = ""
    var co2ScrubberRating = ""

    fun gammaRateAsInt(): Int = Integer.parseInt(gammaRate, 2)

    fun epsilonRateAsInt(): Int = Integer.parseInt(epsilonRate, 2)

    fun oxygenGeneratorRatingAsInt(): Int = Integer.parseInt(oxygenGeneratorRating, 2)

    fun co2ScrubberRatingAsInt(): Int = Integer.parseInt(co2ScrubberRating, 2)

    fun powerConsumption(): Int = gammaRateAsInt() * epsilonRateAsInt()

    fun liveSupportRating(): Int = oxygenGeneratorRatingAsInt() * co2ScrubberRatingAsInt()

    fun calculateDiagnosticReport(rawData: List<String>) : DiagnosticReport {
        val gammaRate: StringBuilder = java.lang.StringBuilder()
        val epsilonRate: StringBuilder = java.lang.StringBuilder()
        for (i in 0 until  rawData[0].length step 1) {
            val sumOfOnes = rawData.count { entry: String -> entry.subSequence(i, i + 1) == "1" }
            gammaRate.append(if (sumOfOnes >= rawData.size/2) "1" else "0")
            epsilonRate.append(if (sumOfOnes >= rawData.size/2) "0" else "1")
        }
        val gammaRateString = gammaRate.toString()
        val epsilonRateString = epsilonRate.toString()
        println("Gamma rate is '$gammaRateString' and epsilon rate is $epsilonRate")
        this.gammaRate = gammaRateString
        this.epsilonRate = epsilonRateString
        return this
    }
}