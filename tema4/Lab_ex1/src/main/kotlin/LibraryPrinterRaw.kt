class LibraryPrinterRaw : LibraryPrinter
{
    override fun printBooks(books: Set<Book>)
    {
        books.forEach()
        {
            println("Autor: " + it.getAuthor())
            println("Text: " + it.getText())
            println("Name: " + it.getName())
            println("Publisher: " + it.getPublisher())
            println("Price: " + it.getPrice())
            println()
        }
    }
}