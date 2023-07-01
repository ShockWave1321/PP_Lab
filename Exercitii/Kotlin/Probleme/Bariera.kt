package Kotlin.Probleme

import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.sync.Mutex

suspend fun sum(h: Map<Int, Int>, bariera: Bariera): List<Int> = coroutineScope {
    val list = mutableListOf<Int>()
    for (i in 0 until h.size - 1) {
        val key1 = h.keys.elementAt(i)
        val key2 = h.keys.elementAt(i + 1)
        list.add(h[key1]!! + h[key2]!!)
    }
    bariera.notify()
    list
}

suspend fun temp(bariera: Bariera) = coroutineScope {
    delay(5000)
    bariera.notify()
}

class Bariera(val n: Int) {
    private val mutex = Mutex()
    private val channel = Channel<Unit>(Channel.UNLIMITED)
    private var counter = 0

    suspend fun wait() {
        mutex.lock()
        if (counter != n) {
            mutex.unlock()
            channel.receive()
        } else {
            mutex.unlock()
        }
    }

    suspend fun notify() {
        mutex.lock()
        counter++
        if (counter == n) {
            channel.send(Unit)
        }
        mutex.unlock()
    }
}

fun main() = runBlocking {
    val h = mutableMapOf(4 to 6, 3 to 6, 2 to 9, 7 to 7)
    val bariera = Bariera(4)
    val c1 = async { sum(h, bariera) }
    val c2 = async { sum(h, bariera) }
    val c3 = async { sum(h, bariera) }
    val c4 = async { temp(bariera) }
    bariera.wait()
    println(c1.await())
    println(c2.await())
    println(c3.await())
}
