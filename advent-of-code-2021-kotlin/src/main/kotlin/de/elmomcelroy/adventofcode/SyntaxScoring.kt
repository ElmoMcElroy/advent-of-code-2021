package de.elmomcelroy.adventofcode

import java.util.*

class SyntaxScoring {

    private val syntaxErrorScoreCounter: MutableMap<Char, Int> = mutableMapOf(Pair(')', 0), Pair(']', 0), Pair('}', 0), Pair('>', 0))
    private val completeBracketPerLines: MutableList<Queue<Char>> = mutableListOf()

    fun calculateSyntaxErrorScore(lines: List<String>): Long {
        lines.forEach { line -> processLine(line.toCharArray()) }
        return calculateSyntaxErrorScore()
    }

    fun calculateIncompleteScore(lines: List<String>): Long {
        lines.forEach { line -> processLine(line.toCharArray()) }
        return calculateIncompleteScore()
    }

    private fun processLine(line: CharArray) {
        val stack = ArrayDeque<Char>()
        line.forEach { bracket ->
            when (bracket) {
                '(' -> stack.push(bracket)
                '[' -> stack.push(bracket)
                '{' -> stack.push(bracket)
                '<' -> stack.push(bracket)
                ')' -> if (checkClosingBracket(stack.pop(), '(', bracket)) return
                ']' -> if (checkClosingBracket(stack.pop(), '[', bracket)) return
                '}' -> if (checkClosingBracket(stack.pop(), '{', bracket)) return
                '>' -> if (checkClosingBracket(stack.pop(), '<', bracket)) return
                else -> {
                    error("Char is not a bracket")
                }
            }
        }

        val closingBrackets = LinkedList<Char>()
        while (stack.isNotEmpty()) {
            when (stack.pop()) {
                '(' -> closingBrackets.add(')')
                '[' -> closingBrackets.add(']')
                '{' -> closingBrackets.add('}')
                '<' -> closingBrackets.add('>')
                else -> {
                    error("Char is not a bracket")
                }
            }
        }
        completeBracketPerLines.add(closingBrackets)
    }

    private fun checkClosingBracket(previousBracket: Char, neededBracket: Char, bracket: Char): Boolean {
        return if (previousBracket != neededBracket) {
            syntaxErrorScoreCounter[bracket] = syntaxErrorScoreCounter[bracket]!! + 1
            true
        } else {
            false
        }
    }

    private fun calculateIncompleteScore(): Long {

        val incompleteScores = completeBracketPerLines.map { queue ->
            var totalScore = 0L
            while (queue.isNotEmpty()) {
                totalScore *= 5
                totalScore += when (queue.poll()) {
                    ')' -> 1
                    ']' -> 2
                    '}' -> 3
                    '>' -> 4
                    else -> {
                        error("Char is not a bracket")
                    }
                }
            }
            totalScore
        }.sorted()

        return incompleteScores[incompleteScores.size/2]
    }

    private fun calculateSyntaxErrorScore(): Long {
        return syntaxErrorScoreCounter[')']!!.times(3L) +
                syntaxErrorScoreCounter[']']!!.times(57L) +
                syntaxErrorScoreCounter['}']!!.times(1197L) +
                syntaxErrorScoreCounter['>']!!.times(25137L)
    }

}