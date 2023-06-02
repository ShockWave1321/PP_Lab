package P1.Lab

import kotlin.properties.Delegates

var n : Int by Delegates.vetoable(1)
{
        _, _, newValue -> newValue > 0
}
fun main()
{
    print("Numar de duplicare: ")
    n = readln().toInt()
    val list = listOf(1, 2, 3)
    val newList = list.flatMap { i -> List(n) {i} }
    println(newList)
}

