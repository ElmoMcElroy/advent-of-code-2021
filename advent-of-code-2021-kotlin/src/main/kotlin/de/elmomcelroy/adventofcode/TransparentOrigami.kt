package de.elmomcelroy.adventofcode

class TransparentOrigami {

    val paper: MutableList<MutableList<Boolean>> = mutableListOf()
    val instructions: MutableList<Pair<Char, Int>> = mutableListOf()

    fun foldByInstructions(input: List<String>): Array<BooleanArray> {
        instructions.clear()
        var foldedPaper: Array<BooleanArray> = parseInput(input)
        for (instruction in instructions) {
            foldedPaper = fold(foldedPaper, instruction)
        }
        return foldedPaper
    }

    fun firstFold(input: List<String>): Int {
        instructions.clear()
        val paper = parseInput(input)
        val foldedPaper = fold(paper, instructions.first())
        return countDots(foldedPaper)
    }

    private fun fold(paper: Array<BooleanArray>, instruction: Pair<Char, Int>): Array<BooleanArray> {
        val foldedPaper: Array<BooleanArray>
        val foldValue = instruction.second
        if (instruction.first == 'x') {
            foldedPaper = Array(foldValue) { BooleanArray(paper[0].size) }
            for (x in paper.indices step 1) {
                for (y in paper[x].indices step 1) {
                    if (x > foldValue) {
                        val foldedX = foldValue - (x - foldValue)
                        foldedPaper[foldedX][y] = foldedPaper[foldedX][y] || paper[x][y]
                    } else if (x < foldValue) {
                        foldedPaper[x][y] = paper[x][y]
                    }
                }
            }
        } else {
            foldedPaper = Array(paper.size) { BooleanArray(instruction.second) }
            for (x in paper.indices step 1) {
                for (y in paper[x].indices step 1) {
                    if (y > foldValue) {
                        val foldedY = foldValue - (y - foldValue)
                        foldedPaper[x][foldedY] = foldedPaper[x][foldedY] || paper[x][y]
                    } else if (y < foldValue) {
                        foldedPaper[x][y] = paper[x][y]
                    }
                }
            }
        }
        printPaper(foldedPaper)
        return foldedPaper
    }

    private fun countDots(foldedPaper: Array<BooleanArray>): Int {
        var dotsCount = 0
        for (x in foldedPaper.indices step 1) {
            for (y in foldedPaper[x].indices step 1) {
                if (foldedPaper[x][y]) {
                    dotsCount++
                }
            }
        }
        return dotsCount
    }

    private fun parseInput(input: List<String>): Array<BooleanArray> {
        val paperInput: MutableList<Pair<Int, Int>> = mutableListOf()
        input.forEach { row ->
            if (row.startsWith("fold along")) {
                val splittedInstruction = row.replace("fold along ", "").trim().split("=")
                instructions.add(Pair(splittedInstruction.first().single(), splittedInstruction.last().toInt()))
            } else if (row.isNotEmpty()){
                val coordinate = row.split(",")
                paperInput.add(Pair(coordinate.first().toInt(), coordinate.last().toInt()))
            }
        }
        val maxX = paperInput.maxOfOrNull { coordinate -> coordinate.first }
        val maxY = paperInput.maxOfOrNull { coordinate -> coordinate.second }
        val paper: Array<BooleanArray> = Array(maxX!! + 1) { BooleanArray(maxY!! + 1) }
        paperInput.forEach { coordinate -> paper[coordinate.first][coordinate.second] = true }
        return paper
    }

    fun printPaper(paper: Array<BooleanArray>) {
        for (x in paper.indices step 1) {
            for (y in paper[x].indices step 1) {
                if (paper[x][y]) print("#") else print(".")
            }
            println()
        }
        println()
    }
}