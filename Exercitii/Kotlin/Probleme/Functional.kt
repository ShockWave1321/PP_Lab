package Kotlin.Probleme

import java.util.Scanner
import kotlin.random.Random

fun main(args: Array<String>) {
    val A = List(100) {
        2 *  Random(System.nanoTime()).nextInt(5000)
    }
    var n = Scanner(System.`in`).nextInt()
    println(A.take(n).sumOf{it * it})
}