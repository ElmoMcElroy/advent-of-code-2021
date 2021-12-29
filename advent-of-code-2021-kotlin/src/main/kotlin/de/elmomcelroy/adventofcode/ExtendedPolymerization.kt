package de.elmomcelroy.adventofcode

import java.lang.StringBuilder

class ExtendedPolymerization {

    private val rules: MutableMap<String, String> = mutableMapOf()

    /**
     * rekursiv. baum links n schritte. dann rechts bis n-ter schritt erreicht
     * merken welche buchstaben bei n-ten schritt
     */


    fun calculateScore(polymer: String): Long {
        val charsCount = mutableMapOf<Char, Long>()
        polymer.forEach{
            charsCount[it] = charsCount.getOrDefault(it, 0) + 1
        }
        return charsCount.values.maxOrNull()!! - charsCount.values.minOrNull()!!
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