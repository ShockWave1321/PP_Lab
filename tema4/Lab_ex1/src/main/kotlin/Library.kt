class Library(private val books: MutableSet<Book>)
{
    fun getBooks(): Set<Book> = books.toSet()
    fun addBook(book: Book)
    {
        books.add(book)
    }
    fun findAllByAuthor(author: String): Set<Book>
    {
        return books.filter { it.getAuthor() == author }.toSet()
    }
    fun findAllByName(name: String): Set<Book>
    {
        return books.filter { it.getName() == name }.toSet()
    }
    fun findAllByPublisher(publisher: String): Set<Book>
    {
        return books.filter { it.getPublisher() == publisher }.toSet()
    }
}