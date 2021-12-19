package de.elmomcelroy.adventofcode

class LavaTubes {

    fun calculateBiggestBasins(heightMap: List<List<Int>>): Long {
        return findBasins(heightMap).map { basin -> basin.size.toLong() }
                .sorted()
                .asReversed()
                .take(3)
                .reduce {left, right -> left * right }
    }

    fun findBasins(heightMap: List<List<Int>>): List<List<Pair<Int, Int>>> {
        val lowestPoints = findLowestPoints(heightMap)
        val basins = mutableListOf<List<Pair<Int, Int>>>()
        for (lowestPoint in lowestPoints) {
            val basin = mutableListOf<Pair<Int, Int>>()
            addToBasin(heightMap, basin, lowestPoint.first, lowestPoint.second)
            basins.add(basin)
        }
        return basins
    }

    private fun addToBasin(heightMap: List<List<Int>>, basin: MutableList<Pair<Int, Int>>, row: Int, column: Int) {
        if (basin.contains(Pair(row, column)) || heightMap[row][column] == 9) {
            return
        }
        basin.add(Pair(row, column))
        // left
        if(column > 0) {
            addToBasin(heightMap, basin, row, column - 1)
        }
        // right
        if (column < heightMap[row].lastIndex) {
            addToBasin(heightMap, basin, row, column + 1)
        }
        // above
        if (row > 0) {
            addToBasin(heightMap, basin, row - 1, column)
        }
        // below
        if (row < heightMap.lastIndex) {
            addToBasin(heightMap, basin, row + 1, column)
        }

    }

    fun calculateRiskLevel(heightMap: List<List<Int>>): Int {
        val lowestPoints = findLowestPoints(heightMap)
        return lowestPoints.sumOf { point -> heightMap[point.first][point.second] + 1 }
    }

    fun findLowestPoints(heightMap: List<List<Int>>): List<Pair<Int, Int>> {
        val heightMapArray: Array<IntArray> = parseToArray(heightMap)
        val lowestPoints = mutableListOf<Pair<Int, Int>>()

        for(row in heightMapArray.indices) {
            for (column in heightMapArray[row].indices) {
                // left
                val isLeftHigher = column == 0 || heightMap[row][column-1] > heightMap[row][column]
                // right
                val isRightHigher = column == heightMapArray[row].lastIndex || heightMap[row][column+1] > heightMap[row][column]
                // above
                val isAboveHigher = row == 0 || heightMap[row-1][column] > heightMap[row][column]
                // below
                val isBelowHigher = row == heightMapArray.lastIndex || heightMap[row+1][column] > heightMap[row][column]

                if (isLeftHigher && isRightHigher && isAboveHigher && isBelowHigher) {
                    lowestPoints.add(Pair(row, column))
                }
            }
        }
        return lowestPoints
    }

    private fun parseToArray(heightMap: List<List<Int>>): Array<IntArray> {
        val heightMapArray: Array<IntArray> = Array(heightMap.size) { IntArray(heightMap.first().size) }
        for (row in heightMap.indices step 1) {
            val rowArray = heightMap[row]
            for (column in rowArray.indices step 1) {
                heightMapArray[row][column] = rowArray[column]
                print("${heightMapArray[row][column]}")
            }
            println()
        }
        return heightMapArray
    }
}