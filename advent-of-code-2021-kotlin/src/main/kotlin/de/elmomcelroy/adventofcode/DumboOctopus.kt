package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils

class DumboOctopus {

    fun simulateSimultaneouslyFlash(energyLevel: List<List<Int>>): Int {
        val energyLevelArray = Utils.parseToArray(energyLevel)
        var stepCounter = 1

        while (!checkSimultaneouslyIncrease(energyLevelArray)) {
            stepCounter++
            if (stepCounter > 1000) {
                return -1
            }
        }
        return stepCounter
    }

    fun simulateSteps(energyLevel: List<List<Int>>, totalSteps: Int): Int {
        val energyLevelArray = Utils.parseToArray(energyLevel)

        var flashCount = 0
        for (step in 0 until totalSteps) {
            flashCount += flashesAfterEnergyLevelIncrease(energyLevelArray)
        }
        return flashCount
    }

    private fun flashesAfterEnergyLevelIncrease(energyLevel: Array<IntArray>): Int {
        increaseEnergyLevel(energyLevel)

        var flashCount = 0
        for(row in energyLevel.indices) {
            for (column in energyLevel[row].indices) {
                if (energyLevel[row][column] == -1) {
                    energyLevel[row][column] = 0
                    flashCount++
                }
                print(energyLevel[row][column])
            }
            println()
        }
        println()
        return flashCount
    }

    private fun checkSimultaneouslyIncrease(energyLevel: Array<IntArray>): Boolean {
        increaseEnergyLevel(energyLevel)

        var simultaneousIncrease = true
        for(row in energyLevel.indices) {
            for (column in energyLevel[row].indices) {
                if (energyLevel[row][column] == -1) {
                    energyLevel[row][column] = 0
                } else {
                    simultaneousIncrease = false
                }
                print(energyLevel[row][column])
            }
            println()
        }
        println()
        return simultaneousIncrease
    }

    private fun increaseEnergyLevel(energyLevel: Array<IntArray>) {
        for(row in energyLevel.indices) {
            for (column in energyLevel[row].indices) {
                energyLevel[row][column] = energyLevel[row][column] + 1
            }
        }
        var changedEnergyLevel = true
        while (changedEnergyLevel) {
            changedEnergyLevel = false
            for(row in energyLevel.indices) {
                for (column in energyLevel[row].indices) {
                    if (energyLevel[row][column] > 9) {
                        increaseFlashingEnergyLevel(energyLevel, row, column)
                        changedEnergyLevel = true
                    }
                }
            }
        }
    }

    // set flashed to -1
    private fun increaseFlashingEnergyLevel(energyLevel: Array<IntArray>, row: Int, column: Int) {
        if (energyLevel[row][column] == -1) {
            return
        }
        if (energyLevel[row][column] in 0 until  10) {
            energyLevel[row][column] = energyLevel[row][column] + 1
            return
        }
        if (energyLevel[row][column] >  9) {
            energyLevel[row][column] = -1
        }

        // left
        if(column > 0) {
            increaseFlashingEnergyLevel(energyLevel, row, column - 1)
        }
        // right
        if (column < energyLevel[row].lastIndex) {
            increaseFlashingEnergyLevel(energyLevel, row, column + 1)
        }
        // above
        if (row > 0) {
            increaseFlashingEnergyLevel(energyLevel, row - 1, column)
        }
        // below
        if (row < energyLevel.lastIndex) {
            increaseFlashingEnergyLevel(energyLevel, row + 1, column)
        }
        // left above
        if(column > 0 && row > 0) {
            increaseFlashingEnergyLevel(energyLevel, row - 1, column - 1)
        }
        // right above
        if (column < energyLevel[row].lastIndex && row > 0) {
            increaseFlashingEnergyLevel(energyLevel, row -1, column + 1)
        }
        // left below
        if (column > 0 && row < energyLevel.lastIndex) {
            increaseFlashingEnergyLevel(energyLevel, row + 1, column - 1)
        }
        // right below
        if (column < energyLevel[row].lastIndex && row < energyLevel.lastIndex) {
            increaseFlashingEnergyLevel(energyLevel, row + 1, column + 1)
        }
    }

}