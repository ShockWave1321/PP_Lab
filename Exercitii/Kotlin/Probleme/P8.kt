package Kotlin.Probleme

import java.io.File
import java.nio.file.Paths
import java.util.Scanner
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock


class Client(
    private val mutex: ReentrantLock,
    private val number: Int
): Thread() {

    private var map : MutableMap<Int,Int> = mutableMapOf()
    private var start: Int = 0
    private var end: Int = 0

    private var ready: Condition = mutex.newCondition()

    private fun setReady(){
        mutex.lock()
        ready.signal()
        mutex.unlock()
    }

    fun receive(map: MutableMap<Int,Int>,start: Int,end:Int){
        this.map = map
        this.start = start
        this.end = end

        setReady()
    }

    override fun run(){
        mutex.lock()
        ready.await()

        println(name)
        for (i in start until end){
            if (map[i] == null){
                continue
            }

            print("${map[i]} -> ")
            map[i] = map[i]!!*number
            println("${map[i]}")
        }

        mutex.unlock()
    }
}

class Server(
    private val mutex: ReentrantLock,
    private val map: MutableMap<Int,Int>,
    private val path: String,
    private val clients: List<Client>
): Thread() {
    init {
        isDaemon = true
    }

    override fun run() {
        map.putAll(readFile())

        val unitOfWork = map.keys.size/clients.size

        for (client in clients.withIndex()) {
            client.value.receive(map, client.index * unitOfWork, (client.index + 1) * unitOfWork)
        }
    }

    private fun readFile(): MutableMap<Int, Int> {
        val input = Scanner(File(path))
        var i = 0

        val map = mutableMapOf<Int,Int>()

        while(input.hasNextInt()){
            map[i] = input.nextInt()
            i++
        }

        return map
    }
}

fun main() {
    val mutex = ReentrantLock()
    val map = mutableMapOf<Int, Int>()

    val c1 = Client(mutex, 1)
    val c2 = Client(mutex, 2)
    val c3 = Client(mutex, 3)
    val c4 = Client(mutex, 4)

    val clients = listOf<Client>(c1, c2, c3, c4)

    val path = Paths.get("").toAbsolutePath().toString()


    val serv = Server(mutex, map, "$path/numere", clients)

    serv.start()
    clients.forEach { it.start() }

    print(map)
}