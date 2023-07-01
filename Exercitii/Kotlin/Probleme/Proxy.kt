package Kotlin.Probleme
import java.io.File
import java.util.Scanner

interface SchimbarePret {
    fun schimbaPret(pret: Float)
}

interface Observer {
    fun update(pret: Float, user: String)
}

class PretProxy : SchimbarePret {
    private val pret = Pret()
    private var authenticate = false
    private val lista = mutableListOf<Observer>()
    private var lastLoggedUser = ""

    private fun notifyAll(pret: Float, user: String) {
        for(elem in lista) {
            elem.update(pret, user)
        }
    }

    fun addObserver(observer: Observer) {
        lista.add(observer)
    }

    override fun schimbaPret(pret: Float) {
        if(!authenticate) {
            print("User: ")
            lastLoggedUser = readln()
            authenticate = true
        }

        this.pret.schimbaPret(pret)
        notifyAll(pret, lastLoggedUser)
    }
}
class Pret : SchimbarePret {
    private var pret: Float = 0F

    override fun schimbaPret(pret: Float) {
        this.pret = pret
    }
}


class LogObserver : Observer {
    private val fisier = File("log")
    override fun update(pret: Float, user: String) {
        fisier.appendText("[LOG] User ${user} has changed the price to ${pret}\n")
    }
}


fun main(args: Array<String>) {
    val colaProxy = PretProxy()
    colaProxy.addObserver(LogObserver())
    val scanner = Scanner(System.`in`)
    while (true) {
        print("Pret Nou: ")
        val pretNou: Float = scanner.nextFloat()
        colaProxy.schimbaPret(pretNou)
    }
}