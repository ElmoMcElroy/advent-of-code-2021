package de.elmomcelroy.adventofcode


class HydrothermalVenture(private val yDimension: Int, private val xDimension: Int) {

    private val field: Array<IntArray> = Array(yDimension) { IntArray(xDimension) }
    val vectors = mutableListOf<Vector>()

    fun findHydrothermalVents(hydrothermalVentsInput: List<String>, diagonal: Boolean): Int {
        vectors.addAll(parse(hydrothermalVentsInput))
        applyVectors(diagonal)
        printField()
        return findVents()
    }

    private fun findVents(): Int {
        var sum = 0
        for (y in field.indices step 1) {
            for (x in field[y].indices step 1) {
                if (field[y][x] > 1)
                    sum++
            }
        }
        return sum
    }

    private fun applyVectors(diagonal: Boolean) {
        vectors.forEach { vector -> applyVector(vector, diagonal) }
    }

    private fun applyVector(vector: Vector, diagonal: Boolean) {
        if (isPositiveVerticalVector(vector)) {
            for (y in vector.startY .. vector.endY ) {
                field[y][vector.startX] += 1
            }
        } else if (isNegativeVerticalVector(vector)) {
            for (y in vector.startY downTo vector.endY ) {
                field[y][vector.startX] += 1
            }
        } else if (isPositiveHorizontalVector(vector)) {
            for (x in vector.startX .. vector.endX ) {
                field[vector.startY][x] += 1
            }
        } else if (isNegativeHorizontalVector(vector)) {
            for (x in vector.startX downTo vector.endX ) {
                field[vector.startY][x] += 1
            }
        } else if (!diagonal) {
            println("Skip Diagonal Vector: $vector")
        } else if (isRightDownVector(vector)) {
            var y = vector.startY
            var x = vector.startX
            while (x <= vector.endX) {
                field[y][x] += 1
                y--
                x++
            }
        } else if (isRightUpVector(vector)) {
            var y = vector.startY
            var x = vector.startX
            while (x <= vector.endX) {
                field[y][x] += 1
                y++
                x++
            }
        } else if (isLeftDownVector(vector)) {
            var y = vector.startY
            var x = vector.startX
            while (x >= vector.endX) {
                field[y][x] += 1
                y--
                x--
            }
        } else if (isLeftUpVector(vector)) {
            var y = vector.startY
            var x = vector.startX
            while (x >= vector.endX) {
                field[y][x] += 1
                y++
                x--
            }
        }
    }

    private fun isPositiveVerticalVector(vector: Vector) =
        vector.startX == vector.endX && vector.startY < vector.endY

    private fun isNegativeVerticalVector(vector: Vector) =
        vector.startX == vector.endX && vector.startY > vector.endY

    private fun isPositiveHorizontalVector(vector: Vector) =
        vector.startX < vector.endX && vector.startY == vector.endY

    private fun isNegativeHorizontalVector(vector: Vector) =
        vector.startX > vector.endX && vector.startY == vector.endY

    private fun isRightDownVector(vector: Vector) =
        vector.startX < vector.endX && vector.startY > vector.endY

    private fun isRightUpVector(vector: Vector) =
        vector.startX < vector.endX && vector.startY < vector.endY

    private fun isLeftDownVector(vector: Vector) =
        vector.startX > vector.endX && vector.startY > vector.endY

    private fun isLeftUpVector(vector: Vector) =
        vector.startX > vector.endX && vector.startY < vector.endY

    private fun parse(hydrothermalVentsInput: List<String>): List<Vector> {
        return hydrothermalVentsInput.map { rawVector ->
                val (start, end) = rawVector.split(" -> ")
                val (startX, startY) = start.split(",")
                val (endX, endY) = end.split(",")
                Vector(startX.toInt(), startY.toInt(), endX.toInt(), endY.toInt())
            }

    }

    private fun printField() {
        for (row in field.indices step 1) {
            for (column in field[row].indices step 1) {
                print(field[row][column])
                print(" ")
            }
            println()
        }
    }

}