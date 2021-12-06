package de.elmomcelroy.utils

import java.io.FileNotFoundException

class Utils {

    companion object {
        fun readFileAsString(fileName: String): List<String> {
            if (fileName.isNotEmpty()) {
                return this::class.java.getResourceAsStream(fileName).bufferedReader().readLines()
            } else {
                throw FileNotFoundException("Empty fileName. Cannot read file")
            }
        }

        fun readFileAsInt(fileName: String): List<Int> {
            if (fileName.isNotEmpty()) {
                return this::class.java.getResourceAsStream(fileName)
                        .bufferedReader()
                        .readLines()
                        .map { s: String -> s.toInt() }
            } else {
                throw FileNotFoundException("Empty fileName. Cannot read file")
            }
        }
    }

}