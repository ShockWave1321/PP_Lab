package P1.Teme



fun main()
{
    val numbers = listOf(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)
    val filteredNumbers = numbers.filter { it >= 5 }
    println("Numerelor mai mici ca 5: $filteredNumbers")

    val pairs = filteredNumbers.zipWithNext().filterIndexed { index, _ -> index % 2 == 0 }
    println("Perechi numere mai mici ca 5: $pairs")

    val multipliedNumbers = pairs.map { (first, second) -> first * second }
    println("Inmultirea perechilor: $multipliedNumbers")

    val sum = multipliedNumbers.sum()
    println("Suma: $sum")
}