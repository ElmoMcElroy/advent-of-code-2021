package de.elmomcelroy.adventofcode

class ExtendedPolymerization {

    private val rules: MutableMap<String, String> = mutableMapOf()
    private val knownCharCounts: MutableMap<Pair<String, Int>, Map<Char, Long>> = mutableMapOf()

    /**
     * rekursiv. baum links n schritte. dann rechts bis n-ter schritt erreicht
     * merken welche buchstaben für polymer Paar bei n-ten schritt
     *
     *  Template:     NNCB
     *  After step 1: NCNBCHB
     *  After step 2: NBCCNBBBCBHCB
     *  After step 3: NBBBCNCCNBBNBNBBCHBHHBCHB
     *  After step 4: NBBNBNBBCCNBCNCCNBBNBBNBBBNBBNBBCBHCBHHNHCBBCBHCB
     */
    fun countCharacters(polymerRules: List<String>, steps: Int): MutableMap<Char, Long> {
        rules.clear()
        knownCharCounts.clear()
        val charsCountAll = mutableMapOf<Char, Long>()
        val polymer = parseInput(polymerRules)
        charsCountAll[polymer.first()] = charsCountAll.getOrDefault(polymer.first(), 0) + 1

        for (pos in 2 until polymer.length + 1) {
            val charsCount = applyRulesRecursive(polymer.substring(pos - 2, pos), steps, 0)
            charsCount.keys.forEach { keyChar -> charsCountAll.merge(keyChar, charsCount[keyChar]!!) { prev, one -> prev + one } }
            println("charsCount: $charsCount")
            println("charsCountAll: $charsCountAll")
            println()
        }

        return charsCountAll
    }

    private fun applyRulesRecursive(polymerPair: String, maxStep: Int, step: Int): Map<Char, Long> {

        if (step == maxStep) {
            return mutableMapOf(Pair(polymerPair.last(), 1))
        }
        val newChar = rules[polymerPair]!!
        val charsCountMerged: Map<Char, Long>
        if (!knownCharCounts.containsKey(Pair(polymerPair, step))) {
            val charsCountLeft = applyRulesRecursive(polymerPair.first() + newChar, maxStep, step + 1)
            val charsCountRight = applyRulesRecursive(newChar + polymerPair.last(), maxStep, step + 1)
            charsCountMerged = (charsCountLeft.keys + charsCountRight.keys).associateWith{ charsCountLeft.getOrDefault(it, 0).plus(charsCountRight.getOrDefault(it, 0)) }

            knownCharCounts[Pair(polymerPair, step)] = charsCountMerged
        } else {
            charsCountMerged = knownCharCounts[Pair(polymerPair, step)]!!
        }
//        println("KnownCharCount[${Pair(polymerPair, step)}]: ${knownCharCounts[Pair(polymerPair, step)]}")
//        println()
        return charsCountMerged

    }


    fun calculateScore(charsCount: MutableMap<Char, Long>): Long {
        return charsCount.values.maxOrNull()!! - charsCount.values.minOrNull()!!
    }

    fun calculateScore(polymer: String): Long {
        val charsCount = mutableMapOf<Char, Long>()
        polymer.forEach{
            charsCount[it] = charsCount.getOrDefault(it, 0) + 1
        }
        return calculateScore(charsCount)
    }

    fun extendPolymer(polymerRules: List<String>, steps: Int): String {
        var polymer = parseInput(polymerRules)
        for (iter in 0 until steps ) {
            polymer = applyRules(polymer)
        }
        return polymer
    }

    private fun applyRules(polymer: String): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(polymer.first())
        for (pos in 2 until polymer.length + 1) {
            val polymerPair = polymer.substring(pos - 2, pos)
            stringBuilder.append(rules[polymerPair])
            stringBuilder.append(polymerPair.last())
        }
        return stringBuilder.toString()
    }

    private fun parseInput(polymerRules: List<String>): String {
        polymerRules.subList(2, polymerRules.size)
                .forEach { rule ->
                    val split = rule.split(" -> ")
                    rules.putIfAbsent(split.first(), split.last())
                }
        return polymerRules[0]
    }
}