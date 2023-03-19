open class Content (
    private var author: String,
    private var text: String,
    private var name: String,
    private var publisher: String
)
{
    fun getAuthor():String
    {
        return author
    }
    fun setAuthor(author: String)
    {
        if(author != "")
            this.author = author
        else
            println("Autor invalid!\n")
    }
    fun getText(): String
    {
        return text
    }
    fun setText(text: String)
    {
        if(text != "")
            this.text = text
        else
            println("Text invalid!\n")
    }
    fun getName(): String
    {
        return name
    }
    fun setName(name: String)
    {
        if(name != "")
            this.name = name
        else
            println("Nume invalid!\n")
    }
    fun getPublisher(): String
    {
        return publisher
    }
    fun setPublisher(publisher: String)
    {
        if(publisher != "")
            this.publisher = publisher
        else
            println("Publicatie invalida!\n")
    }
}
