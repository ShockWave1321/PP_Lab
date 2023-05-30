package P4

import kotlin.properties.Delegates
import P1.isPrime

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