import java.time.LocalDate
import java.time.LocalTime

class NoteManager
{
    fun printNotes(user:User)
    {
        if(!user.notes.isEmpty())
        {
            for(note in user.notes)
            {
                println("Titlu: "+ note.titlu)
                println("Data: " + note.data)
                println("Ora: " + note.ora)
                println("Continut: " + note.continut)
                println()
            }
        }
        else
        {
            println("Nu exista notite")
        }
    }
    fun newNote(user:User)
    {
        println("Titlu: ")
        val title = readLine().toString()
        println("Continut:")
        val continut = readLine().toString()
        val data = LocalDate.now()
        val ora = LocalTime.now()
        user.notes.add(Note(title, data, ora, continut))
    }
    fun deleteNote(user:User, title:String)
    {
        var ok = 0
        for(note in user.notes)
        {
            if(note.titlu == title)
            {
                ok = 1
                user.notes.remove(note)
                println("Notita stearsa")
            }
        }
        if(ok == 0)
        {
            println("Nu exita notita")
        }
    }
    /*fun loadNote(user:User ,title:String): Note?
    {
        for(note in user.notes)
        {
            if(title == note.titlu)
            {
                return note
            }
        }
        println("Nu exista notita")
        return null
    }*/
}