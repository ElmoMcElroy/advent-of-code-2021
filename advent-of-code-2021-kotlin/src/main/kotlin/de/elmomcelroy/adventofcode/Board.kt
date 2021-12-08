package de.elmomcelroy.adventofcode

class Board {

    private val height = 5
    private val width = 5

    val field: Array<IntArray> = Array(height) { IntArray(width) }
    private val fieldHits: Array<BooleanArray> = Array(height) { BooleanArray(width) }

    fun markFieldIfPossible(inputNumber: Int) {
        for (row in fieldHits.indices step 1) {
            for (column in fieldHits[row].indices step 1) {
                if (field[row][column] == inputNumber) {
                    fieldHits[row][column] = true
                }
            }
        }
    }

    fun checkBoardForWin(): Boolean {
        var win = true
        // check rows
        for (row in fieldHits.indices step 1) {
            win = true
            for (column in fieldHits[row].indices step 1) {
                if (!fieldHits[row][column])
                    win = false
            }
            if (win) return true
        }
        // check columns
        for (column in fieldHits.indices step 1) {
            win = true
            for (row in fieldHits[column].indices step 1) {
                if (!fieldHits[row][column])
                    win = false
            }
            if (win) return true
        }
        return win
    }

    fun sumUnmarkedNumbers(): Int {
        var sum = 0
        for (row in fieldHits.indices step 1) {
            for (column in fieldHits[row].indices step 1) {
                if (!fieldHits[row][column]) sum += field[row][column]
            }
        }
        return sum
    }

    fun printBoardFields(){
        for (row in field.indices step 1) {
            for (column in field[row].indices step 1) {
                print(field[row][column])
                print(" ")
            }
            println()
        }
    }

    fun printBoardHits(){
        for (row in fieldHits.indices step 1) {
            for (column in fieldHits[row].indices step 1) {
                print(fieldHits[row][column])
                print(" ")
            }
            println()
        }
    }
}
