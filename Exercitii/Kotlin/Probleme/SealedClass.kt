package Kotlin.Probleme

sealed class Clasa<out T>() {
    data class Banca<T>(var value: T?, val next: Clasa<T>) : Clasa<T>()
    object Empty : Clasa<Nothing>()

    fun isEmpty(): Boolean {
        return when (this) {
            is Banca -> false
            is Empty -> true
        }
    }

    fun isFull(): Boolean {
        return when (this) {
            is Banca -> (this.prezenta() == 10)
            is Empty -> false
        }
    }

    fun prezenta(): Int {
        return when (this) {
            is Banca -> {if (value != null) { 1 } else { 0 } + this.next.prezenta()}
            is Empty -> 0
        }
    }

    fun listaStudenti(){
        when (this) {
            is Banca -> {
                if(this.value != null)
                    println(this.value.toString())
                else
                    println("\t\t\t\tBanca Goala")
                this.next.listaStudenti()
            }
            is Empty -> println("----------")
        }
    }

    fun aVenit(newValue: @UnsafeVariance T?): Clasa<T> {
        when (this) {
            is Banca<T> -> {
                if(!isFull()) {
                    return if (this.value == null) {
                        this.value = newValue
                        this
                    } else {
                        Banca(this.value, this.next.aVenit(newValue))
                    }
                } else {
                    println("Laborator plin scuze ${newValue}")
                    return this
                }
            }
            is Empty -> return Banca(newValue, Empty)
        }
    }

}

fun main() {
    var laborator: Clasa<String> = Clasa.Empty
    println("Laborator gol: ${laborator.isEmpty()}")
    val studenti: List<String?> = listOf("Alex", "Andrei", "Ana", "Alexandra", null, "Bobert", null, "Alejandro", "Mihai", "Dumitru","Paco","Cristi","Ion")
    for(student in studenti){
        println("${student} vrea sa intre in Laborator")
        laborator = laborator.aVenit(student)
    }
    println("\nStudenti intrati in Laborator: ")
    laborator.listaStudenti()
    println("Au venit ${laborator.prezenta()}")
}