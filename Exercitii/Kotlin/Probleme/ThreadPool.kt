package Kotlin.Probleme

import java.util.concurrent.*

fun main() {

    val vector = arrayOf(4,2,1,3)
    val a = 2

    val exec = Executors.newFixedThreadPool(3)

    val t1 = {
        for (i in vector.indices)
            vector[i] = vector[i] * a
    }

    val t2 = {
        vector.sort()
    }

    val t3 = {
        println("Vector final: ${vector.joinToString()}")
    }

    exec.execute(t1)
    exec.execute(t2)
    exec.execute(t3)

    exec.shutdown()
    exec.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS)
}