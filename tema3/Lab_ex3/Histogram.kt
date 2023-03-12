//utilizam File din Java.io pentru a deschide fisierul text
import java.io.File
import kotlin.collections.toSortedMap as toSortedMap

fun GetUniqueWordCount(all_words : List<String>) : MutableMap<String, Int> {
    //functia pentru calculul cuvintelor unice
    val result = mutableMapOf<String, Int>()
    for (word in all_words)
    {
        word.lowercase()
        if (!result.containsKey(word))
        {
            result[word] = 1
        }
        else
        {
            result[word] = result.getValue(word) + 1
        }
    }
    return result
}

fun GetUniqueCharCount(all_chars : List<String>) : MutableMap<Char, Int> {
    //functia pentru calculul caracterelor unice
    val result = mutableMapOf<Char, Int>()
    all_chars.forEach {
        for (c in it) {
            if (c in 'a'..'z' || c in 'A'..'Z') {
                if (!result.containsKey(c)) {
                    result[c.uppercaseChar()] = 1
                } else {
                    result[c] = result.getValue(c) + 1
                }
            }
        }
    }
    return result
}

fun SortByHitCount(items : MutableMap<Char, Int>, how: Boolean) : MutableMap<Int, Char>{
    //functia de sortare a caracterelor, dupa valoare (frecventa), atat crescator cat si descrescator, in functie de how
    var result = mutableMapOf<Int, Char>()
    items.forEach{
        result[it.value] = it.key
    }
    if(!how)
        result = result.toSortedMap()
    else
        result = result.toSortedMap(compareByDescending { it })
    return result
}

//functia main()
fun main(args : Array<String>){
    //citim liniile din fisier
    val lines = File("Fisier.txt").reader().readText()
    //construim un array de cuvinte, seprand prin spatiu
    val words = lines.split(" ","\n")

    //eliminam semnele de punctuatie de pe marginile cuvintelor
    val trim_words = mutableListOf<String>()
    words.forEach {
        val filter = it.trim(',', '.', '"', '?', '!')
        trim_words += filter.toLowerCase()
        print(filter + " ")
    }
    println("\n")

    //construim o lista cu toate caracterele folosite 'A..Z'
    val chars = mutableListOf<String>()
    trim_words.forEach {
        for (c in it){
            if (c in 'a'..'z' || c in 'A'..'Z') {
                chars += c.toUpperCase().toString()
                print(c.toUpperCase())
            }
        }
        print(" ")
    }
    println("\n")

    //Pentru constructia histogramelor, R foloseste un mecanism prin care asociaza caracterelor unice, numarul total de aparitii (frecventa)
    // 1. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati cuvintele unice din trim_words
    // 2. Construiti in Kotlin acelasi mecanism de masurare a frecventei elementelor unice si afisati caracterele unice din chars
    // 3. Pentru frecventele caracterelor unice caclulate anterior si
    //      A. Afisati perechile (frecventa -> Caracter) sortate crescator si descrescator
    //      B. afisati graficele variatiei de frecventa sortate anterior crescator si descrescator si concatenati-le intr-un grafic de puncte

    //construim histograma pentru cuvinte
    RHistogram.BuildHistogram(trim_words.toTypedArray(), "Words", true)
    //construim histograma pentru caractere
    RHistogram.BuildHistogram(chars.toTypedArray(), "Chars", true)
    var word_map = GetUniqueWordCount(trim_words)
    var char_map = GetUniqueCharCount(chars)
    println("Frecventa cuvinte:")
    for(word in word_map)
    {
        println(" ${word.key} - ${word.value} ")
    }
    println("Frecventa litere:")
    for(char in char_map)
    {
        println(" ${char.key} - ${char.value} ")
    }

    var sort_char = SortByHitCount(char_map,false)
    var arr_sort_char : Array<Int> = sort_char.keys.toTypedArray()
    println("Afisare crescatoare frecventa:")
    for(key in sort_char)
    {
        println(" ${key.key} - ${key.value} ")
    }

    sort_char = SortByHitCount(char_map,true)
    arr_sort_char = arr_sort_char.plus(sort_char.keys.toTypedArray())

    println("Afisare descrescatoare frecventa:")
    for(key in sort_char)
    {
        println(" ${key.key} - ${key.value} ")
    }
    //arr_sort_char.forEach { print(it.toString() + " ") }
    RHistogram.BuildHistogram(arr_sort_char,"Freq_word",false)
}
