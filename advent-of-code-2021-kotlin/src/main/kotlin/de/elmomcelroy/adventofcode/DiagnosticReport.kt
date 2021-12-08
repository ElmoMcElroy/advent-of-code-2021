package de.elmomcelroy.adventofcode

class DiagnosticReport {
    var gammaRate: String = "0"
    var epsilonRate: String = "0"
    var oxygenGeneratorRating: String = "0"
    var co2ScrubberRating: String = "0"

    fun gammaRateAsInt(): Int = Integer.parseInt(gammaRate, 2)

    fun epsilonRateAsInt(): Int = Integer.parseInt(epsilonRate, 2)

    fun oxygenGeneratorRatingAsInt(): Int = Integer.parseInt(oxygenGeneratorRating, 2)

    fun co2ScrubberRatingAsInt(): Int = Integer.parseInt(co2ScrubberRating, 2)

    fun powerConsumption(): Int = gammaRateAsInt() * epsilonRateAsInt()

    fun liveSupportRating(): Int = oxygenGeneratorRatingAsInt() * co2ScrubberRatingAsInt()

    fun calculatePowerConsumption(rawData: List<String>) : Int {
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
        return powerConsumption()
    }

    fun calculateLifeSupportRating(rawData: List<String>): Int {
        oxygenGeneratorRating = calcOxygenGeneratorRating(rawData).first()
        co2ScrubberRating = calcCo2ScrubberRating(rawData).first()
        println("oxygenGeneratorRating is '$oxygenGeneratorRating' and co2ScrubberRating is $co2ScrubberRating")
        return liveSupportRating()
    }

    private fun calcOxygenGeneratorRating(oxygenGeneratorRating: List<String>): List<String> {
        var tempRating = oxygenGeneratorRating
        for (i in 0 until oxygenGeneratorRating[0].length step 1) {
            tempRating = filterByLeadingOne(tempRating, i)
            if (tempRating.size == 1) return tempRating
        }
        if (tempRating.size > 1) calcOxygenGeneratorRating(tempRating)
        return tempRating
    }

    private fun filterByLeadingOne(rawData: List<String>, index: Int): List<String> {
        val sumOfOnes = rawData.count { entry: String -> entry.subSequence(index, index + 1) == "1" }
        return rawData.filter { entry -> if (isLeading(sumOfOnes, rawData.size)) entry.subSequence(index, index + 1) == "1" else entry.subSequence(index, index + 1) == "0" }
    }

    private fun calcCo2ScrubberRating(oxygenGeneratorRating: List<String>): List<String> {
        var tempRating = oxygenGeneratorRating
        for (i in 0 until oxygenGeneratorRating[0].length step 1) {
            tempRating = filterByLeadingZero(tempRating, i)
            if (tempRating.size == 1) return tempRating
        }
        if (tempRating.size > 1) calcOxygenGeneratorRating(tempRating)
        return tempRating
    }

    private fun filterByLeadingZero(rawData: List<String>, index: Int): List<String> {
        val sumOfOnes = rawData.count { entry: String -> entry.subSequence(index, index + 1) == "1" }
        return rawData.filter { entry -> if (isLeading(sumOfOnes, rawData.size)) entry.subSequence(index, index + 1) == "0" else entry.subSequence(index, index + 1) == "1" }
    }

    private fun isLeading(sum: Int, size: Int): Boolean {
        return if (size % 2 == 0) (sum >= size / 2) else (sum > size / 2)
    }
}