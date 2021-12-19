package de.elmomcelroy.adventofcode

class SevenSegmentDisplay {

    fun sumDecodeOutput(recordedSegments: List<String>): Int {
        return decodeOutput(recordedSegments).sum()
    }

    fun decodeOutput(recordedSegments: List<String>): List<Int> {
        val parsedRows = parse(recordedSegments)
        return parsedRows.map { parsedRow ->
            val decodedSegments = determineDigits(parsedRow)
            val decodedNumber = parsedRow.second
                .map { encodedDigit -> encodedDigit.toCharArray().sorted().joinToString("") }
                .map { sortedEncodedDigit ->
                    println("Encoded digit: $sortedEncodedDigit")
                    decodedSegments[sortedEncodedDigit]
                }
                .joinToString("")
                .toInt()
            println("Decoded Number: $decodedNumber")
            decodedNumber
        }
    }

    fun determineDigits(parsedRecord: Pair<List<String>, List<String>>): Map<String, Int> {
        val input = ArrayList(parsedRecord.first)
        input.addAll(parsedRecord.second)
        val charInput = input.map { stringInput -> stringInput.toCharArray() }
        val segments = mutableMapOf<Int, Char>()


        // Segment 5 -> zwei- und sechstellige mit 4 gleichen Buchstaben
        val distinctDigit25: List<CharArray> = charInput.filter { chars -> chars.size == 2 || chars.size == 6 }
            .map { chars -> chars.sortedArray().joinToString("") }
            .distinct()
            .map { s: String -> s.toCharArray() }

        val segments25Count = mutableMapOf<Char, Int>()
        distinctDigit25.forEach { chars ->
            chars.forEach { char ->
                segments25Count.putIfAbsent(char, 0)
                segments25Count[char] = segments25Count[char]!! + 1
            }
        }
        segments.putIfAbsent(5, segments25Count.filter { entry -> entry.value == 4 }.map { entry -> entry.key }.first())
        // Segement 2 -> Buchstabe der in nicht in Segment 5
        val digit1 = charInput.first { chars -> chars.size == 2 }
        val unknownSegemetnsInDigit1 = digit1.filter { chars -> chars != segments[5] }
        segments.putIfAbsent(2, unknownSegemetnsInDigit1.first())


        // Segement 0 Buchstabe der in 7 aber nicht in 1
        val digit7 = charInput.first { chars -> chars.size == 3 }
        segments.putIfAbsent(0, digit7.filter { chars -> chars != segments[2] && chars != segments[5] }.first())

        // Segment 3 -> einziges Segment bei allen vier- und fünfstelligen vorhanden
        val distinctDigit45: List<CharArray> = charInput.filter { chars -> chars.size == 4 || chars.size == 5 }
            .map { chars -> chars.sortedArray().joinToString("") }
            .distinct()
            .map { s: String -> s.toCharArray() }

        val segments45Count = mutableMapOf<Char, Int>()
        distinctDigit45.forEach { chars ->
            chars.forEach { char ->
                segments45Count.putIfAbsent(char, 0)
                segments45Count[char] = segments45Count[char]!! + 1
            }
        }
        segments.putIfAbsent(3, segments45Count.filter { entry -> entry.value == 4 }.map { entry -> entry.key }.first())
        // Segment 1 -> Buchstabe der im vierstelligen ist und noch nicht gesetzt
        val digit4 = charInput.first { chars -> chars.size == 4 }
        val unknownSegemetnsInDigit4 = digit4.filter { chars -> chars != segments[2] && chars != segments[3] && chars != segments[5] }
        segments.putIfAbsent(1, unknownSegemetnsInDigit4.first())



        // Segement 6 -> Buchstabe der noch nicht besetzt ist und bei den sechsstelligen drei mal vorkommt
        val distinctSixSegments: List<CharArray> = charInput.filter { chars -> chars.size == 6 }
            .map { chars -> chars.sortedArray().joinToString("") }
            .distinct()
            .map { s: String -> s.toCharArray() }
            .map {
                    chars -> chars.filter { char -> char != segments[2] && char != segments[5] && char != segments[0] && char != segments[1] && char != segments[3] }.toCharArray()
            }

        val charCounts = mutableMapOf<Char, Int>()
        distinctSixSegments.forEach { chars ->
            chars.forEach { char ->
                charCounts.putIfAbsent(char, 0)
                charCounts[char] = charCounts[char]!! + 1
            }
        }
        segments.putIfAbsent(6, charCounts.filter { entry -> entry.value == 3 }.map { entry -> entry.key }.first())

        // Segement 4 -> Buchstabe der in siebenstelligen noch nicht besetzt
        val lastChar = charInput.filter { chars -> chars.size == 7 }
            .map { chars ->
                chars.filter { char -> char != segments[2] && char != segments[5] && char != segments[0] && char != segments[1] && char != segments[3] && char != segments[6] }
                    .toCharArray()
            }
            .first()
        segments.putIfAbsent(4, lastChar.first())

        println("Segmente: $segments")
        /*
             000      xxx     dddd
            1   2    x   x   e    a
            1   2    x   x   e    a
             333      xxx     ffff
            4   5    4   x   g    b
            4   5    4   x   g    b
             666      666     cccc
         */
        val digitsToString = mutableMapOf<String, Int>()
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[1]!!, segments[2]!!, segments[4]!!, segments[5]!!, segments[6]!!).sorted().joinToString(""), 0)
        digitsToString.putIfAbsent(charArrayOf(segments[2]!!, segments[5]!!).sorted().joinToString(""), 1)
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[2]!!, segments[3]!!, segments[4]!!, segments[6]!!).sorted().joinToString(""), 2)
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[2]!!, segments[3]!!, segments[5]!!, segments[6]!!).sorted().joinToString(""), 3)
        digitsToString.putIfAbsent(charArrayOf(segments[1]!!, segments[3]!!, segments[2]!!, segments[5]!!).sorted().joinToString(""), 4)
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[1]!!, segments[3]!!, segments[5]!!, segments[6]!!).sorted().joinToString(""), 5)
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[1]!!, segments[3]!!, segments[4]!!, segments[5]!!, segments[6]!!).sorted().joinToString(""), 6)
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[2]!!, segments[5]!!).sorted().joinToString(""), 7)
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[1]!!, segments[2]!!, segments[3]!!, segments[4]!!, segments[5]!!, segments[6]!!).sorted().joinToString(""), 8)
        digitsToString.putIfAbsent(charArrayOf(segments[0]!!, segments[1]!!, segments[2]!!, segments[3]!!, segments[5]!!, segments[6]!!).sorted().joinToString(""), 9)

        return digitsToString
    }

    private fun parse(recordedSegments: List<String>): ArrayList<Pair<List<String>, List<String>>> {
        val parsedRows = ArrayList<Pair<List<String>, List<String>>>()
        for (row in recordedSegments) {
            val inAndOutput = row.split("|").map { entry -> entry.trim() }
            val input = inAndOutput.first().split(" ")
            val output = inAndOutput.last().split(" ")
            parsedRows.add(Pair(input, output))
        }
        return parsedRows
    }

    fun simpleOutputDigits(recordedSegments: List<String>): Int {
        val parsedRecords = parse(recordedSegments)
        return parsedRecords
            .map { record -> record.second }
            .sumOf { outputs ->
                outputs
                    .filter { output -> output.length == 2 || output.length == 3 || output.length == 4 || output.length == 7 }
                    .size
            }
    }
}
