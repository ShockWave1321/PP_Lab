import java.io.File

fun deleteSpaces(text : String) : String
{
    val regex = "\\s+".toRegex()
    return text.replace(regex, " ")
}
fun deleteNextLines(text : String) : String
{
    val regex = "\n*".toRegex()
    return text.replace(regex, "")
}
fun deleteNumberPage(text : String) : String
{
    val regex = "\\s+[0-9]$".toRegex()
    return text.replace(regex, "")
}
fun main(args: Array<String>)
{
    print("Nume eBook:")
    val input = readln()
    val file1 = File("$input.txt")
    val file2 = File("eBook_new.txt")
    val reader = file1.bufferedReader()
    var text = file1.readLines().toMutableList()
    reader.close()

    print("Optiuni editare:\n1 - eliminare spatii multiple\n2 - Eliminare linii multiple\n3 - Eliminare numar pagina\n4 - Salvare modificari\n")
    var x = 0
    var temp2 = text
    while(x != 4) {
        print("Optiune: ")
        x = readln().toInt()
        val temp1 = mutableListOf<String>()

        when (x) {
            1 ->{ for(line in temp2)
                temp1.add(deleteSpaces(line))
                temp2 = temp1
            }
            2 ->{ for(line in temp2)
                { val tmp = deleteNextLines(line)
                     if(tmp != "")
                        temp1.add(tmp)
                }
                temp2 = temp1
            }
            3 ->{ for(line in temp2)
                temp1.add(deleteNumberPage(line))
                temp2 = temp1
            }
            4 -> text = temp2
        }
    }
    file2.bufferedWriter().use { writer ->
        for(line in text) {
            writer.write(line)
            writer.newLine()
        }
    }

}
/*
var text = "     Multe    goluri \n\n nu ii asa  21      1 "
text = deleteSpaces(text)
text = deleteNextLines(text)
text = deleteNumberPage(text)
println(text)*/
