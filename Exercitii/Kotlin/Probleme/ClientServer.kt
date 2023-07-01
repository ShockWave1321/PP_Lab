package Kotlin.Probleme

import java.io.File
import java.lang.Thread
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.locks.ReentrantLock
import kotlin.math.round

object CriticalData {
    val data = mutableMapOf<Int, Int>()

    val mutex = ReentrantLock()
}

class Server(val clients: List<Client>) : Thread() {
    init {
        this.setDaemon(true)
        this.start()
    }

    override fun run() {
        val textFile = File("./pushi.txt").readText()

        CriticalData.mutex.lock()

        textFile.trim().split("\\s+".toRegex()).forEach {
            val number = it.toInt()

            CriticalData.data[number] = number
        }

        println(CriticalData.data)

        var keysCopy = CriticalData.data.keys.toList()
        val dropCount = Math.round(CriticalData.data.keys.size.toFloat() / clients.size)

        for (c in clients) {
            keysCopy.take(dropCount).forEach({ c.indexes.add(it) })

            c.indexes.add(-1)

            keysCopy = keysCopy.drop(dropCount)
        }

        CriticalData.mutex.unlock()
    }
}

class Client(val name_: String, val multiplier: Int) : Thread(name_) {
    public val indexes = LinkedBlockingQueue<Int>()

    init {
        this.start()
    }

    override fun run() {
        while (true) {
            val index = indexes.take()

            if (index == -1) {
                break
            }

            if (index != null) {
                println(name + ": asteapta sa proceseze")
                CriticalData.mutex.lock()
                val dataProcessed = CriticalData.data[index]

                if (dataProcessed != null) {
                    println(name + ": proceseaza ${dataProcessed} * ${multiplier}")
                    CriticalData.data[index] = dataProcessed * multiplier
                }

                println(name + ": a procesat si a eliberat mutexul")
                CriticalData.mutex.unlock()
            }
        }
    }
}

fun main() {
    val clients =
        listOf(
            Client("client-1", 1),
            Client("client-2", 2),
            Client("client-3", 3),
            Client("client-4", 4)
        )

    Server(clients)

    for (c in clients) {
        c.join()
    }

    println(CriticalData.data)
}