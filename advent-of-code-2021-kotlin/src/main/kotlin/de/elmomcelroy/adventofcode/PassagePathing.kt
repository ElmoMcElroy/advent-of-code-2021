package de.elmomcelroy.adventofcode

class PassagePathing {

    private val possibleOutgoingPaths: MutableMap<String, MutableList<String>> = mutableMapOf()
    private val validPaths: MutableList<MutableList<String>> = mutableListOf()

    fun findPathsWithDoubleVisit(paths: List<String>): Int {
        parseOutgoingPaths(paths)
        validPaths.clear()
        possibleOutgoingPaths["start"]!!.forEach { outgoing ->
            findNextWithADoubleVisit(mutableListOf("start"), outgoing, "")
        }
        println()
        return validPaths.size
    }

    private fun findNextWithADoubleVisit(currentWay: MutableList<String>, currentPoint: String, doubleVisit: String) {
        if (currentPoint == "end") {
            currentWay.add(currentPoint)
            validPaths.add(currentWay)
            println("Valid Path: $currentWay")
            return
        } else if (doubleVisit.isNotEmpty() && (
                (currentPoint == doubleVisit && currentWay.filter { visited -> visited == doubleVisit }.size > 1) ||
                (currentPoint != doubleVisit && currentPoint[0].isLowerCase() && currentWay.contains(currentPoint)))) {
            //println("Current point already visited or first double visited. Path ends here")
            return
        } else if (possibleOutgoingPaths.containsKey(currentPoint)) {

            var newDoubleVisit = doubleVisit
            if (doubleVisit.isEmpty() && currentPoint[0].isLowerCase() && currentWay.contains(currentPoint)) {
                newDoubleVisit = currentPoint
            }

            currentWay.add(currentPoint)
            possibleOutgoingPaths[currentPoint]!!.forEach { nextOutgoing ->
                findNextWithADoubleVisit(currentWay.toMutableList(), nextOutgoing, newDoubleVisit )
            }
        } else {
            //println("No more outgoing ways. Path ends here")
            return
        }
    }

    fun findPaths(paths: List<String>): Int {
        parseOutgoingPaths(paths)
        validPaths.clear()
        possibleOutgoingPaths["start"]!!.forEach { outgoing ->
            findNext(mutableListOf("start"), outgoing)
        }
        println()
        return validPaths.size
    }

    private fun findNext(currentWay: MutableList<String>, currentPoint: String) {
            if (currentPoint == "end") {
                currentWay.add(currentPoint)
                validPaths.add(currentWay)
                println("Valid Path: $currentWay")
                return
            } else if (currentPoint[0].isLowerCase() && currentWay.contains(currentPoint)) {
                //println("Current point already visited. Path ends here")
                return
            } else if (possibleOutgoingPaths.containsKey(currentPoint)) {
                currentWay.add(currentPoint)
                possibleOutgoingPaths[currentPoint]!!.forEach { nextOutgoing ->
                    findNext(currentWay.toMutableList(), nextOutgoing)
                }
            } else {
                //println("No more outgoing ways. Path ends here")
                return
            }
    }

    private fun parseOutgoingPaths(paths: List<String>) {
        possibleOutgoingPaths.clear()
        paths.forEach { path ->
            val splittedPath = path.split("-")
            if (splittedPath.last() != "start" && splittedPath.first() != "end") {
                possibleOutgoingPaths.putIfAbsent(splittedPath.first(), mutableListOf())
                possibleOutgoingPaths[splittedPath.first()]!!.add(splittedPath.last())
            }
            if (splittedPath.first() != "start" && splittedPath.last() != "end") {
                possibleOutgoingPaths.putIfAbsent(splittedPath.last(), mutableListOf())
                possibleOutgoingPaths[splittedPath.last()]!!.add(splittedPath.first())
            }
        }
    }

}