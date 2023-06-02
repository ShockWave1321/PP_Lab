package P1.Lab

import kotlin.properties.Delegates

var intNumber:Int by Delegates.vetoable(0)
{
    _, _, newValue -> newValue.isPrime()
}

fun main()
{
    intNumber = 15
    println(intNumber)
    intNumber = 23
    println(intNumber)
    println(intNumber.isPrime())
}