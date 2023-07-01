package Kotlin.Probleme

class Person(name:String)
{
    fun printMe() = println(this)
}
class Building(val address:String)
{
    inner class Reception(telephone :String)
    {
        fun printAddress() = println(this@Building.address)
    }
}
fun main()
{
    val b = Building("1223")
    b.Reception("0752").printAddress()
}
