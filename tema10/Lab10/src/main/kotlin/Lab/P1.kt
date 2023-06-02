package org.example.Lab.P1

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.*
import java.io.FileWriter
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
fun writeToFile(file: String) {
    val writer = FileWriter(file, true)
    list.forEach {
        value -> writer.write(value.toString()+"\n")
    }
    writer.close()
}

@OptIn(DelicateCoroutinesApi::class)
val mtContext = newFixedThreadPoolContext(2, "mtPool")
var counter = 0
val mutex = Mutex()
val list  = mutableListOf<Int>()
fun main() = runBlocking<Unit> {
    CoroutineScope(mtContext).massiveRun {
        mutex.withLock {
            counter++ //variabila comuna unde vor aparea erori
            list.add(counter)
        }
    }
    val write  = CoroutineScope(mtContext).launch {
        writeToFile("out1.txt")
    }
    write.join()
    println("Numarator = $counter")
}

