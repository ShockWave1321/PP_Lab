package org.example.Lab.P2


import kotlinx.coroutines.*
import kotlin.system.*
import kotlinx.coroutines.channels.*
import java.io.FileWriter


sealed class ContorMsg
object IncContor : ContorMsg()
class GetContor(val response: CompletableDeferred<Int>) : ContorMsg()

sealed class WriterMsg
data class WriteValue(val value: Int) : WriterMsg()
object CloseWriter : WriterMsg()

@OptIn(ObsoleteCoroutinesApi::class)
fun CoroutineScope.writerActor(file : String) = actor<WriterMsg> {
    val writer = FileWriter(file, true)
    for (msg in channel) {
        when (msg)
        {
            is WriteValue -> writer.write(msg.value.toString() + "\n")
            is CloseWriter -> {
                writer.close()
                return@actor
            }
        }
    }
}

@OptIn(ObsoleteCoroutinesApi::class)
fun CoroutineScope.counterActor() = actor<ContorMsg> {
    var contor = 0
    for (msg in channel) {
        when (msg)
        {
            is IncContor -> contor++
            is GetContor -> msg.response.complete(contor)
        }
    }
}

suspend fun massiveRun(action: suspend () -> Unit) {
    coroutineScope {
        val n = 100
        val k = 1000
        val timp = measureTimeMillis {
            val jobs = List(n) {
                launch {
                    repeat(k) {
                        action()
                    }
                }
            }
            jobs.forEach { it.join() }
        }
        println("Am terminat atatea ${n * k} actiuni in $timp ms")
    }
}

fun main() = runBlocking<Unit> {
    val contor = counterActor()
    val writer = writerActor("out2.txt")
    massiveRun()
    {
        contor.send(IncContor)
        writer.send(WriteValue(1))
        //println(contor.onSend)
    }

    val raspuns = CompletableDeferred<Int>()
    contor.send(GetContor(raspuns))
    writer.send(CloseWriter)
    println("Contor = ${raspuns.await()}")
    contor.close()
}

