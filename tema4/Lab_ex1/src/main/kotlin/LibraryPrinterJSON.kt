class LibraryPrinterJSON : LibraryPrinter
{
    override fun printBooks(books: Set<Book>)
    {
        for(book in books)
        {
            println("{")
            println("   \"name\": \"${book.getName()}\"")
            println("   \"author\": \"${book.getAuthor()}\"")
            println("   \"publisher\": \"${book.getPublisher()}\"")
            println("   \"text\": \"${book.getText()}\"")
            println("   \"price\": \"${book.getPrice()}\"")
            println("}")
        }
    }
}
