package P1.Teme

import kotlin.math.abs
import kotlin.math.sqrt

fun XtoY(p1: Pair<Int, Int>, p2: Pair<Int, Int>): Double {
    val xDiff = abs(p1.first - p2.first)
    val yDiff = abs(p1.second - p2.second)

    return sqrt((xDiff * xDiff + yDiff * yDiff).toDouble())
}
fun perimeter(n: Int, coords: List<Pair<Int, Int>>): Double {
    if (n < 3) {
        return 0.0
    }
    val edges = coords.zipWithNext() + Pair(coords.last(), coords.first())
    val distances = edges.map {
        XtoY(it.first, it.second)
    }
    return distances.sum()
}
fun main()
{
    val n = 4
    val coordinates = listOf(
            Pair(0, 0),
            Pair(0, 1),
            Pair(1, 0),
            Pair(1, 1)
    )
    val p = perimeter(n, coordinates)
    println("Perimetru: $p")
}

