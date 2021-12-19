package de.elmomcelroy.adventofcode

import de.elmomcelroy.utils.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Task06Test {

    private val subjectUnderTest = LanternFishSchool()

    @Test
    fun testLanternFishGrowth() {

        val lanternfishes = "3,4,3,1,2"

        val numFishesAfter18 = subjectUnderTest.startGrowth(lanternfishes, 18)
        assertEquals(26, numFishesAfter18, "Number of fishes after 18 days")

        val numFishesAfter80 = subjectUnderTest.startGrowth(lanternfishes, 80)
        assertEquals(5934, numFishesAfter80, "Number of fishes after 80 days")

        val numFishesAfter128 = subjectUnderTest.startGrowth(lanternfishes, 128)
        assertEquals(388976, numFishesAfter128, "Number of fishes after 128 days")

        val numFishesAfter180 = subjectUnderTest.startGrowth(lanternfishes, 180)
        assertEquals(35890123, numFishesAfter180, "Number of fishes after 180 days")
    }

    @Test
    fun testLanternFishGrowthWithFile() {
        val lanternfishes = Utils.readFileAsString("/task06/input.txt")

        val numFishesAfter80 = subjectUnderTest.startGrowth(lanternfishes.first(), 80)
        assertEquals(343441, numFishesAfter80, "Number of fishes after 80 days")
        println("sum of overlap: $numFishesAfter80")
    }

    @Test
    fun testLanternFishGrowthCalculated() {

//        val lanternfishes = "3,4,3,1,2"
//
//        val numFishesAfter18 = subjectUnderTest.startGrowthCalculated(lanternfishes, 18)
//        assertEquals(26, numFishesAfter18, "Number of fishes after 18 days")
//
//        val numFishesAfter80 = subjectUnderTest.startGrowthCalculated(lanternfishes, 80)
//        assertEquals(5934, numFishesAfter80, "Number of fishes after 80 days")
//
//        val numFishesAfter128 = subjectUnderTest.startGrowthCalculated(lanternfishes, 128)
//        assertEquals(388976, numFishesAfter128, "Number of fishes after 128 days")
//
//        val numFishesAfter180 = subjectUnderTest.startGrowthCalculated(lanternfishes, 180)
//        assertEquals(35890123, numFishesAfter180, "Number of fishes after 180 days")

//        val numFishesAfter256 = subjectUnderTest.startGrowthCalculated(lanternfishes, 256)
//        assertEquals(26984457539, numFishesAfter256, "Number of fishes after 256 days")
    }

    @Test
    fun testLanternFishGrowthCalculatedParallel() {

        val lanternfishes = "3,4,3,1,2"

        val numFishesAfter18 = subjectUnderTest.startGrowthCalculatedParallel(lanternfishes, 18)
        assertEquals(26, numFishesAfter18, "Number of fishes after 18 days")

        val numFishesAfter80 = subjectUnderTest.startGrowthCalculatedParallel(lanternfishes, 80)
        assertEquals(5934, numFishesAfter80, "Number of fishes after 80 days")

        val numFishesAfter128 = subjectUnderTest.startGrowthCalculatedParallel(lanternfishes, 128)
        assertEquals(388976, numFishesAfter128, "Number of fishes after 128 days")

        val numFishesAfter180 = subjectUnderTest.startGrowthCalculatedParallel(lanternfishes, 180)
        assertEquals(35890123, numFishesAfter180, "Number of fishes after 180 days")

        val numFishesAfter256 = subjectUnderTest.startGrowthCalculatedParallel(lanternfishes, 256)
        assertEquals(26984457539, numFishesAfter256, "Number of fishes after 256 days")
    }

    @Test
    fun testLanternFishGrowthCalculatedWithFile() {
        val lanternfishes = Utils.readFileAsString("/task06/input.txt")

        val numFishesAfter80 = subjectUnderTest.startGrowthCalculatedParallel(lanternfishes.first(), 80)
        assertEquals(343441, numFishesAfter80, "Number of fishes after 80 days")
        println("sum of overlap: $numFishesAfter80")

        val numFishesAfter256 = subjectUnderTest.startGrowthCalculatedParallel(lanternfishes.first(), 256)
        assertEquals(1569108373832, numFishesAfter256, "Number of fishes after 80 days")
        println("sum of overlap: $numFishesAfter80")
    }

}