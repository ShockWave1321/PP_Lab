fun main() {
    val content1 = Content("Author 1", "Text 1", "Book 1", "Publisher 1")
    val content2 = Content("Author 2", "Text 2", "Book 2", "Publisher 1")
    val content3 = Content("Author 1", "Text 3", "Book 3", "Publisher 2")

    val book1 = Book(content1,5.59)
    val book2 = Book(content2, 42.00)
    val book3 = Book(content3, 6.49)

    val library = Library(mutableSetOf(book1,book2))
    library.addBook(book3)

    val booksByAuthor = library.findAllByAuthor("Author 1")
    println("Books by Author 1:")
    booksByAuthor.forEach { println(it.getName()) }
    println()

    val booksByName = library.findAllByName("Book 2")
    println("Books named Book 2 by authors:")
    booksByName.forEach { println(it.getAuthor()) }
    println()

    val booksByPublisher = library.findAllByPublisher("Publisher 1")
    println("Books published by Publisher 1:")
    booksByPublisher.forEach { println(it.getName()) }
    println()

    val lpRAW = LibraryPrinterRaw()
    val lpHTML = LibraryPrinterHTML()
    val lpJSON = LibraryPrinterJSON()

    println("Books by Author 1 RAW:\n")
    lpRAW.printBooks(booksByAuthor)
    println()

    println("Books by Author 1 in HTML:")
    lpHTML.printBooks(booksByAuthor)
    println()

    println("Books by Author 1 in JSON:")
    lpJSON.printBooks(booksByAuthor)
    println()
}