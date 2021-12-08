package de.elmomcelroy.adventofcode

class Bingo {

    val draws: ArrayList<Int> = arrayListOf()
    val boards: ArrayList<Board> = arrayListOf()

    var winningNumber: Int = 0
    var roundsPlayed: Int = 0

    fun play(bingoRawInput: List<String>, letTheWookieWin: Boolean): Int {
        parseRawInput(bingoRawInput)

        for (draw in draws) {
            boards.forEach { board -> board.markFieldIfPossible(draw) }
            val tempboard: ArrayList<Board> = arrayListOf()
            tempboard.addAll(boards)
            roundsPlayed++

            for (board in tempboard) {
                if(board.checkBoardForWin()) {
                    if (letTheWookieWin) {
                        if (boards.size == 1) {
                            winningNumber = draw
                            return board.sumUnmarkedNumbers()
                        }
                        boards.remove(board)
                    } else {
                        winningNumber = draw
                        return board.sumUnmarkedNumbers()

                        board.printBoardFields()
                        board.printBoardHits()
                    }
                }
            }

        }
        return -1
    }

//    private fun playRound(): List<Board> {
//
//    }

    private fun parseRawInput(bingoRawInput: List<String>) {
        draws.addAll(bingoRawInput.first().split(",").map { draw -> draw.toInt() })

        val bingoBoards = bingoRawInput.subList(2, bingoRawInput.size)
            .filter { row -> row.isNotBlank() }
            .chunked(5)
        bingoBoards.forEach { rawBoard -> parseBoard(rawBoard) }
    }

    private fun parseBoard(rawBoard: List<String>) {
        val board = Board()
        for (row in rawBoard.indices step 1) {
            val rowArray = rawBoard[row].trim().replace("  ", " ").split(" ").map { draw -> draw.toInt() }
            for (column in rowArray.indices step 1) {
                board.field[row][column] = rowArray[column]
            }
        }
        boards.add(board)
    }

}