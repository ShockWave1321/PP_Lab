package Kotlin.Probleme
val f = {x: Int -> 3 * x - 1 }

class CollectionFunctor<T>(val map: MutableMap<T, T>) {
    fun map(function: (T) -> T): CollectionFunctor<T> {
        for(item in map) {
            map[item.key] = function(item.value)

        }
        return CollectionFunctor(map)
    }

    override fun toString(): String {
        return "CollectionFunctor(map=$map)"
    }
}

fun main(args: Array<String>) {
    val map = mutableMapOf<Int, Int>(
        1 to 2,
        2 to 4,
        3 to 9,
    )
    val text  = CollectionFunctor<Int>(map).map(f)

    println(text.map(f))

}