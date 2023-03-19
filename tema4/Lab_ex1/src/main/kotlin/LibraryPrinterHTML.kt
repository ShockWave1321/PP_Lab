class LibraryPrinterHTML : LibraryPrinter
{
    override fun printBooks(books: Set<Book>) {
        books.forEach {
        println("<html>")
        println("\t<head><title>${it.getName()}</title></head>")
        println("\t<body>")
        println("\t\t<h1>${it.getPublisher()}</h1>")
        println("\t\t<ul>")
        println("\t\t\t<li>${it.getText()}</li>")
        println("\t\t\t<li>${it.getPrice()}</li>")
        println("\t\t</ul>")
        println("\t</body>")
        println("</html>")
        }
    }
}