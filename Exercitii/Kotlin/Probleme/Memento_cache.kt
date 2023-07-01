package Kotlin.Probleme

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.util.concurrent.ConcurrentHashMap

val cache = ConcurrentHashMap<Long, Long>()

suspend fun fibbo(n: Long): Long = coroutineScope{
    if (n in cache.keys ){
        println("Vom lua o valoare din cache  $n -> ${cache[n]}")
        return@coroutineScope cache[n]!!
    }

    if ( n < 2 )
        return@coroutineScope 1
    else {
        val result1 = async {  fibbo(n - 1) }.await()
        val result2 = async {  fibbo(n - 2) }.await()

        cache[n] = result1 + result2

        return@coroutineScope cache[n]!!
    }
}

suspend fun main() {
    print(fibbo(49))
}
//https://github.com/RealKC/PP