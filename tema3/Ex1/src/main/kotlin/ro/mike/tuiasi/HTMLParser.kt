package ro.mike.tuiasi

import org.jsoup.Jsoup

class RSSfeed(val url : String)
{
    class Item(val title: String, val link: String, val description: String, val pubDate: String)
    fun getFeed(): List<Item>
    {
        val doc = Jsoup.connect(url).get()
        val elem = doc.select("item")
        val items = mutableListOf<Item>()

        for (e in elem)
        {
            val title = e.select("title").text()
            val link = e.select("link").text()
            val desc = e.select("description").text()
            val pd = e.select("pubDate").text()
            items.add(Item(title, link, desc, pd))
        }
        return items
    }
}

fun main()
{
    val url = "http://rss.cnn.com/rss/edition.rss"
    val rss = RSSfeed(url)
    val feed = rss.getFeed()

    var i = 1
    for (item in feed)
    {
        println("Item: ${i++}")
        println("Title: ${item.title}")
        println("Link: ${item.link}")
    }
}