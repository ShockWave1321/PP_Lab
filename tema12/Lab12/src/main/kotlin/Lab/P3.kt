package P1.Lab

fun main()
{
    val map : Map<Int,String> = mutableMapOf(
        1 to "abc",
        2 to "def",
        3 to "ghi"
    )
    println(map)
    println(map.map { it.value to it.key }.toMap())
}

