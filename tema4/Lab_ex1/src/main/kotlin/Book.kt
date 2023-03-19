class Book(
    private val data: Content,
    private var price: Double
)
{
    override fun toString():String
    {
        return data.toString()
    }
    fun getAuthor(): String
    {
        return data.getAuthor()
    }
    fun getName(): String
    {
        return data.getName()
    }
    fun getText(): String
    {
        return data.getText()
    }
    fun getPublisher(): String
    {
        return data.getPublisher()
    }
    fun hasAuthor(author:String):Boolean
    {
        if(data.getAuthor()==author)
            return true
        return false
    }
    fun hasTitle(name:String):Boolean
    {
        if(data.getName()==name)
            return true
        return false
    }
    fun isPublishedBy(publisher:String):Boolean
    {
        if(data.getPublisher()==publisher)
            return true
        return false
    }
    fun setPrice(price: Double)
    {
        if(price>0)
            this.price = price
        else
            println("Pret invalid!\n")
    }
    fun getPrice():Double
    {
        return price
    }
}