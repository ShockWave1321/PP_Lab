package org.example.ex3

class SimpleThread: Thread()
{
    override fun run() {
        println("Instanta clasei derivate din Thread ${Thread.currentThread()} s-a executat.")
    }
}
class SimpleRunnable: Runnable
{
    override fun run() {
        println("Instanta clasei care implementeaza Runnable ${Thread.currentThread()} s-a executat.")
    }
}

fun main(args: Array<String>)
{
    object : Thread()
    {
        override fun run() {
            println("Sunt in thread-ul singleton ${Thread.currentThread()}")
        }
    }.start()
    val t1= SimpleThread()
    t1.run()
    println("t1")
    val t2= SimpleRunnable()
    t2.run()
    println("t2")
    val thread = Thread {
        println("Thread lambda ${Thread.currentThread()} s-a executat.")
    }
    thread.start()
}
