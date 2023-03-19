fun main(args: Array<String>) {
    val nm = NoteManager()
    println("User: ")
    val name = readLine().toString()
    println("Option:\n" +
            "1.Print notes\n" +
            "2.Creare notita\n" +
            "3.Stergere notita\n" +
            //"4.Incarcare notita\n" +
            "5.Iesire\n")
    var opt = 0
    while(opt!=5)
    {
        opt = readln().toInt()
        val user = User(name)
        when(opt)
        {
            1 -> nm.printNotes(user)
            3 -> {
                println("Titlu notita de sters: ")
                val titlu = readLine().toString()
                nm.deleteNote(user,titlu)
            }
            2 -> nm.newNote(user)
            5 -> opt = 5
            else ->
            {
                println("Optiune invalida")
            }
        }

    }
}