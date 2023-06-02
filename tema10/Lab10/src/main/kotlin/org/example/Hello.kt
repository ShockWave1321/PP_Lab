package org.example

import kotlinx.coroutines.*
import kotlinx.coroutines.debug.*

suspend fun computeValue(): String = coroutineScope {
    val one = async { computeOne() }
    val two = async { computeTwo() }
    combineResults(one, two)
}

suspend fun combineResults(one: Deferred<String>, two: Deferred<String>): String =
    one.await() + two.await()

suspend fun computeOne(): String {
    delay(5000)
    return "4"
}

suspend fun computeTwo(): String {
    delay(20000)
    return "2"
}

@OptIn(ExperimentalCoroutinesApi::class)
fun main() = runBlocking {
    DebugProbes.install()
    val deferred = async { computeValue() }
    // Delay for some time
    delay(1000)
    // Dump running coroutines
    DebugProbes.dumpCoroutines()
    println("\nDumping only deferred")
    DebugProbes.printJob(deferred)
}
