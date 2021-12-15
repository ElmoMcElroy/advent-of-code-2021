package de.elmomcelroy.adventofcode

import java.util.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong

class LanternFishSchool {

    private val chunkSize: Int = 100000

    fun startGrowthCalculatedParallel(lanternfishes: String, days: Int): Long {


        val executor = Executors.newFixedThreadPool(10)
        val fishCounterAll: AtomicLong = AtomicLong(0)

        val fishesByDay: HashMap<Int, Int> = HashMap()
        val inputFishes = lanternfishes.split(",").toList()
        val latch = CountDownLatch(inputFishes.size * 2)
        println("latch count on start: ${latch.count}")

        inputFishes.map { raw -> raw.toInt()}
            .forEach { daysLeft ->
                // add to current fish 1 to simulate as newborn fish
                // add fish minus 1 for newborn
                fishesByDay.merge(days - daysLeft + 1, 1, Int::plus)
                fishesByDay.merge(days - daysLeft - 1, 1, Int::plus)

                val worker1 = createWorker(hashMapOf(Pair(days - daysLeft + 1, 1)), fishCounterAll, latch)
                val worker2 = createWorker(hashMapOf(Pair(days - daysLeft - 1, 1)), fishCounterAll, latch)
                executor.execute(worker1)
                executor.execute(worker2)
            }

        latch.await()

        executor.shutdown()
        executor.awaitTermination(10, TimeUnit.SECONDS)
        println("Finished all threads")

        return fishCounterAll.toLong()
    }

    private fun createWorker(fishesByDay: HashMap<Int, Int>, fishCounterAll: AtomicLong, latch: CountDownLatch): Runnable {
        return Runnable {
            var fishCounter: Long = 0
            while (fishesByDay.size > 0) {
                val minLivingFish = fishesByDay.keys.maxOrNull() ?: error("Retrieving min living fish from map failed")
                fishCounter += reproduceFish(minLivingFish, fishesByDay)
                if (fishCounter.mod(1000000000) == 0) {
                    println("Fish counter: $fishCounter")
                }
            }
            fishCounterAll.addAndGet(fishCounter)
            latch.countDown()
            println("latch count: ${latch.count}")
        }
    }

    private fun reproduceFish(lifetime: Int, fishesByDay: HashMap<Int, Int>): Long {
        val numFishes = fishesByDay[lifetime]!!
        fishesByDay.remove(lifetime)

        var leftLife = lifetime - 2

        while (leftLife > 6) {
            leftLife -= 7
            fishesByDay.merge(leftLife, numFishes, Int::plus)
        }
//        println()
        return numFishes.toLong()
    }



    fun startGrowthCalculated(lanternfishes: String, days: Int): Long {

        val fishesByDay: HashMap<Int, Int> = HashMap()
        lanternfishes.split(",").toList()
            .map { raw -> raw.toInt()}
            .forEach { daysLeft ->
                // add to current fish 1 to simulate as newborn fish
                // add fish minus 1 for newborn
                fishesByDay.merge(days - daysLeft + 1, 1, Int::plus)
                fishesByDay.merge(days - daysLeft - 1, 1, Int::plus)
            }

        var fishCounter: Long = 0
        while (fishesByDay.size > 0) {
            val minLivingFish = fishesByDay.keys.minOrNull() ?: error("Retrieving min living fish from map failed")
            reproduceFish(minLivingFish, fishesByDay)
            fishCounter++
            if (fishesByDay[minLivingFish]!! < 1) {
                fishesByDay.remove(minLivingFish)
            }
            if (fishCounter.mod(1000000000) == 0) {
                println("Fish counter: $fishCounter")
            }
        }

        return fishCounter
    }
//
//    private fun reproduceFish(lifetime: Int, fishesByDay: HashMap<Int, Int>) {
//        var daysTillReproduce = 8
//
//        for (daysLeft in lifetime downTo 1) {
////            print("${fish.daysTillReproduce} ")
//            if (daysTillReproduce == 0) {
//                fishesByDay.merge(daysLeft - 1, 1, Int::plus)
//                daysTillReproduce = 6
//            } else {
//                daysTillReproduce--
//            }
//        }
//        fishesByDay.merge(lifetime, 1, Int::minus)
////        println()
//    }



    fun startGrowth(input: String, days: Int): Long {
        val fishes = parseFishes(input)
        val finalFishes = growRecursive(fishes, days)
        return countFishes(finalFishes)
    }

    private fun countFishes(fishes: MutableList<MutableList<Int>>): Long {
//        val chunked = fishes.chunked(chunkSize)
//        val completeBlocks = chunked.subList(0, chunked.size - 1).size
//        return completeBlocks * chunkSize.toLong() + chunked.last().size
        val completeBlocks = fishes.subList(0, fishes.size - 1).size
        return completeBlocks * chunkSize.toLong() + fishes.last().size
    }

    private fun grow(fishes: MutableList<MutableList<Int>>, daysLeft: Int): MutableList<MutableList<Int>> {
        var daycounter = daysLeft
        var tempFishes: MutableList<MutableList<Int>> = copyFishes(fishes)
        var newFishes: MutableList<MutableList<Int>> = tempFishes
        while(daycounter != 0) {
            newFishes = growDay(tempFishes)
            tempFishes = newFishes
            daycounter--
        }
        return newFishes
    }

    private fun growDay(fishes: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val newFishes: MutableList<MutableList<Int>> = copyFishes(fishes)
        fishes.forEachIndexed { index, currentFishSubList ->
            //            val newCurrentFishes = currentFishSubList.toMutableList()
            currentFishSubList.forEachIndexed { subIndex, fish ->
                when (fish) {
                    0 -> {
                        newFishes[index][subIndex] = 6
                        if (newFishes.last().size < chunkSize) {
                            newFishes.last().add(8)
                        } else {
                            newFishes.add(mutableListOf(8))
                        }
                    }
                    else -> {
                        newFishes[index][subIndex] = fish - 1
                    }
                }
            }
        }
        return newFishes
    }

    private fun growRecursive(fishes: MutableList<MutableList<Int>>, daysLeft: Int): MutableList<MutableList<Int>> {
        if (daysLeft == 0) {
            return fishes
        }
        val newFishes: MutableList<MutableList<Int>> = copyFishes(fishes)
        fishes.forEachIndexed { index, currentFishSubList ->
//            val newCurrentFishes = currentFishSubList.toMutableList()
            currentFishSubList.forEachIndexed { subIndex, fish ->
                when (fish) {
                    0 -> {
                        newFishes[index][subIndex] = 6
                        if (newFishes.last().size < chunkSize) {
                            newFishes.last().add(8)
                        } else {
                            newFishes.add(mutableListOf(8))
                        }
                    }
                    else -> {
                        newFishes[index][subIndex] = fish - 1
                    }
                }
            }
        }
//        printFishes(newFishes)
        return grow(newFishes, daysLeft - 1)
    }

    private fun copyFishes(input: MutableList<MutableList<Int>>): MutableList<MutableList<Int>> {
        val copy: MutableList<MutableList<Int>> = mutableListOf()
        input.forEach { sublist -> copy.add(sublist.toMutableList()) }
        return copy
    }

    private fun parseFishes(input: String): MutableList<MutableList<Int>> {
        val fishes = mutableListOf<Int>()
        input.split(",").toList()
            .map { raw -> raw.toInt()}
            .forEach { fish -> fishes.add(fish) }

        printFishes(fishes)
        return mutableListOf(fishes)
    }

    private fun printFishes(fishes: MutableList<Int>) {
        println("$fishes")
    }
}

class Fish(var lifetime: Int, var daysTillReproduce: Int)
