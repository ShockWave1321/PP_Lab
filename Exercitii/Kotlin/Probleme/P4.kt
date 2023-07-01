package Kotlin.Probleme

open class Bike : Cloneable
{
    private var gears: Int = 0
    private var bikeType : String? = null
    var model: String? = null
        private set
    init
    {
        bikeType ="Standard"
        model = "Carpati"
        gears = 4
    }
    public override fun clone():Bike
    {
        return Bike()
    }
    fun makeAdvanced()
    {
        bikeType ="Advanced"
        model = "Jaguar"
        gears = 6
    }
}
fun makeJaguar(basicBike:Bike):Bike
{
    basicBike.makeAdvanced()
    return basicBike
}
fun main()
{
    val bike = Bike()
    val basicBike = bike.clone()
    val advancedBike = makeJaguar(basicBike)
    println("Bicicleta mai buna: "+ advancedBike.model!!)
}