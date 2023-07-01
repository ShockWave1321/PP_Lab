import org.jsoup.Jsoup

import org.jsoup.nodes.Document
import java.io.File

fun main(args: Array<String>) {
    val html_document = Jsoup.connect("https://altex.ro").get()
    val imglinks = mutableListOf<String>()
    for (elem in html_document.body().getElementsByTag("img")) {
        imglinks.add(elem.attributes()["src"].substringAfterLast("https://"))
        //println(elem.attributes()["src"].substringAfterLast("http://"))
    }
    var i = 0
    val folder = File("Imagini")
    if(!folder.isDirectory)
        folder.mkdir()
    else {
        folder.deleteRecursively()
        folder.mkdir()
    }
    for(link in imglinks) {
        val image = File("Imagini/image${i}.jpg")
        if(!link.startsWith("data") && !link.isEmpty() && !link.startsWith("//"))
            image.writeBytes(khttp.get("https://" + link).content)
        i++
    }
}
