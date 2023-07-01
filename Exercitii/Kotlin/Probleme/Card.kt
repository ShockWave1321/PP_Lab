package Kotlin.Probleme

val cart = { xs: List<Int>, ys: List<Int> -> xs.flatMap { x -> ys.map { y -> Pair(x, y) } } }

fun main() {
    val listA = listOf<Int>(1, 2, 3)
    val listB = listOf<Int>(5,8,6,7)

    val uniune = cart(listA, listB).union(cart(listB, listB))
    println(uniune)

    val dict = uniune.groupingBy { it.first }.fold({ key, _ -> key to mutableListOf<Int>() },
        { _, accumulator, element -> accumulator.also { it.second.add(element.second) } }
    ).mapValues { it.value.second }

    print(dict)
}

/*

val cart = { xs: List<Int>, ys: List<Int> -> xs.flatMap { x -> ys.map { y -> Pair(x, y) } } }

fun main(args: Array<String>) {
    val listA = listOf<Int>(1, 2, 3)
    val listB = listOf<Int>(5,8,6,7)

    val uniune = cart(listA, listB).union(cart(listB, listB))
    println(uniune)

    val variatatumefiata = uniune.groupingBy { it.first }.fold({ key, _ -> key to mutableListOf<Int>() },
        { _, accumulator, element -> accumulator.also { it.second.add(element.second) } }
    ).mapValues { it.value.second }

    val variantascurta = uniune.groupBy({ it.first }, {it.second})
    println(variantascurta)

    print(variatatumefiata)
}

val cart  = {x: List<Int>, y: List<Int> -> x.flatMap { a -> y.map { b -> a*b} } }
val reunion = {x: List<Int>, y: List<Int> -> x + y}

fun main(){
    val listA = listOf<Int>(1,2,3)
    val listB = listOf<Int>(2,3,4)

    print(reunion( cart(listA,listB), cart(listB,listB)))

// Output : [2, 3, 4, 4, 6, 8, 6, 9, 12, 4, 6, 8, 6, 9, 12, 8, 12, 16]
}
*/