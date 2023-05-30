fun main()
{
    //var input = readln()
    val input = "aaaabbbccd"
    val newInput = input.asSequence().groupBy { it }.map {
        (char, charList) ->
        if (charList.count() > 1)
            ""+char+charList.count()
        else
            ""+char
    }.joinToString("")
    println(newInput)
}