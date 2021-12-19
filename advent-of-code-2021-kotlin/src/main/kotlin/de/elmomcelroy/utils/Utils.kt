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

        fun readRowsAsInt(fileName: String): List<List<Int>> {
            return readRowsAsInt(fileName, ",")
        }

        fun readRowsAsInt(fileName: String, delimiter: String): List<List<Int>> {
            if (fileName.isNotEmpty()) {
                return this::class.java.getResourceAsStream(fileName)
                        .bufferedReader()
                        .readLines()
                        .map { row: String ->
                            row.split(delimiter).toList().map { rowEntry: String ->  rowEntry.toInt()}
                        }

            } else {
                throw FileNotFoundException("Empty fileName. Cannot read file")
            }
        }

        fun readRowsWithoutSeparatorAsInt(fileName: String): List<List<Int>> {
            if (fileName.isNotEmpty()) {
                return this::class.java.getResourceAsStream(fileName)
                        .bufferedReader()
                        .readLines()
                        .map { row: String ->
                            row.map { it.toString().toInt() }
                        }
            } else {
                throw FileNotFoundException("Empty fileName. Cannot read file")
            }
        }
    }

}