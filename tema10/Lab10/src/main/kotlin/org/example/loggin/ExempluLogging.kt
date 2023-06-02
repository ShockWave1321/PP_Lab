package org.example.loggin

import kotlinx.coroutines.*

fun log(msg: String) = println("[${Thread.currentThread().name}] $msg")

fun main() = runBlocking<Unit> {
    val a = async {
        log("I'm computing a piece of the answer")
        60
    }
    val b = async {
        log("I'm computing another piece of the answer")
        9
    }
    log("The answer is ${a.await() + b.await()}")
}
