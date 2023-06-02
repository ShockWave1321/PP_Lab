package org.example.ex1

import kotlinx.coroutines.*
import kotlin.system.*
suspend fun CoroutineScope.massiveRun(action: suspend () -> Unit) {
    val n = 100
    val k = 1000
    val time = measureTimeMillis {
        val jobs = List(n)
        {
            launch { repeat(k) { action() } }
        }
        jobs.forEach { it.join() }
    }
    println("S-au efectuat ${n * k} operatii in $time ms")
}

@OptIn(DelicateCoroutinesApi::class)
val mtContext = newFixedThreadPoolContext(2, "mtPool")
var counter = 0
fun main() = runBlocking<Unit> {
    CoroutineScope(mtContext).massiveRun {
        counter++ //variabila comuna unde vor aparea erori
    }
    println("Numarator = $counter")
}
