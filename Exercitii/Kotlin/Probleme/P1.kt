package Probleme

fun Int.isPrime() : Boolean {
    if(this <= 1 || this % 2 == 0)
        return false
    for(i in 3.until(this/2).filter{ it % 2 != 0})
    {
        if(this % i == 0)
            return false
    }
    return true
}

fun main()
{
    println(10.isPrime())
    println(127.isPrime())
    println((-2).isPrime())
    println(13.isPrime())
}