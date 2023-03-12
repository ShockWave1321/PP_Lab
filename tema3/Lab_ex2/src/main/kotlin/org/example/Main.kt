import java.io.*;
fun addDict(dict : HashMap<String,String> ,word1:String, word2:String)
{
    dict.put(word1,word2);
}
fun saveInFile(path : String, text:String)
{
    File(path).writeText(text);
}
fun loadDict(path:String) : HashMap<String,String>
{
    var dict : HashMap<String,String>;
    var s : String = "";
    dict = hashMapOf<String,String>()
    File(path).forEachLine{
        dict.put(it.split( "\\")[0], it.split("\\")[1])
    }
    return dict;
}

fun main(args : Array<String>){
    var Dictionar = hashMapOf<String, String>(
        "Once"                to  "Odata",
        "upon"                to  "ca",
        "a"                   to  "",
        "time"                to  "niciodata",
        "there"               to  "acolo",
        "was"                 to  "a fost",
        "an"                  to  "o",
        "old"                 to  "batrana",
        "woman"               to  "femeie",
        "who"                 to  "care",
        "loved"               to  "iubea",
        "baking"              to  "sa gateasca",
        "gingerbread"         to  "turta dulce",
        "She"                 to  "Ea",
        "would"               to  "ar fi",
        "bake"                to  "gatit",
        "gingerbread"         to  "turta dulce",
        "cookies"             to  "biscuiti",
        "cakes"               to  "prajituri",
        "houses"              to  "case",
        "and"                 to  "si",
        "people"              to  "oameni",
        "all"                 to  "toti",
        "decorated"           to  "decorati",
        "with"                to  "cu",
        "chocolate"           to  "ciocolata",
        "peppermint"          to  "menta",
        "caramel"             to  "caramel",
        "candies"             to  "bomboane",
        "colored"             to  "colorate",
        "ingredients"         to  "ingrediente"
    )
    Dictionar = loadDict("Dictionar.txt")
    addDict(Dictionar,"day","zi")
    val Poveste = "Once upon a time there was an old woman " +
                  "who loved baking gingerbread. She would bake " +
                  "gingerbread cookies, cakes, houses and gingerbread" +
                  " people, all decorated with chocolate and peppermint, caramel candies and colored ingredients."

    val words1 = Poveste.split(" ")

    println("Cuvintele din poveste [${words1.count()}]:")
    for (word in words1)
        print(word + " ")

    val words2 = mutableListOf<String>()
    for (word in words1){
        words2.add(word.trim(',','.'))
    }
    var poveste : String = ""
    println("\n")
    println("Povestea tradusa ar suna cam asa:")
    for (item in words2){
        if (Dictionar.contains(item))
        {
            poveste += Dictionar[item]
        }
        else
        {
            poveste += "[$item]"
        }
        if(Dictionar[item] != "")
            poveste += " "
    }
    println(poveste)
    saveInFile("poveste.txt", poveste)
}