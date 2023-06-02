package P1.Lab

fun main()
{
    val input = readln()
    val distinctChars = input.asSequence().distinct().joinToString("")
    println(distinctChars)
}