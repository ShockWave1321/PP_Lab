package P1.Teme

import java.io.File

fun encryptCaesar(word: String, offset: Int): String 
{
    var newWord = ""

    for (i in 0 until word.length) 
    {
        val char = word[i]
        val offsetedChar = when {
            char.isLowerCase() -> ('a' + ((char - 'a' + offset) % 26))
            char.isUpperCase() -> ('A' + ((char - 'A' + offset) % 26))
            else -> char
        }
        newWord += offsetedChar
    }
    return newWord
}

fun main()
{
    val fileName = "/home/student/Laboratoare/Lab12/Lab_ex/src/main/kotlin/Teme/input.txt" // Numele fișierului de intrare
    val offset = 2

    val content = File(fileName).readText()
    val processedContent = content.split(" ").joinToString(" ") { word ->
        if (word.length in 4..7) {
            encryptCaesar(word,offset)
        } else {
            word
        }
    }
    println("Conținutul prelucrat:\n$processedContent")
}
