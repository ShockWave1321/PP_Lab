import requests
import sqlite3
import xml.etree.ElementTree as ET


class Entry:
    title: str
    description: str

    def __init__(self, title, description):
        self.title = title
        self.description = description


class DataBase:
    db = sqlite3.connect("examen.db")

    def __init__(self):
        cu = self.db.cursor()
        cu.execute("CREATE TABLE IF NOT EXISTS EXAMEN(Title VARCHAR(40), Description VARCHAR(255))")

    def add(self, entry: Entry):
        cu = self.db.cursor()
        cu.execute("INSERT INTO EXAMEN (Title, Description) VALUES (?, ?)", (entry.title, entry.description))
        self.db.commit()

    def search(self, keyword):
        cu = self.db.cursor()
        cu.execute("SELECT * FROM EXAMEN WHERE Title LIKE ? OR Description LIKE ?",
                   ('%' + keyword + '%', '%' + keyword + '%'))
        results = cu.fetchall()
        for row in results:
            print(f"Title: {row[0]}\n\nDescription: {row[1]}\n\n")


if __name__ == '__main__':
    rss = requests.get("http://rss.cnn.com/rss/edition_world.rss")
    db = DataBase()
    # db.add(Entry("Vadfid", "valentin"))
    # db.search("vai")
    f = open("test.rss", "wb")
    f.write(rss.content)
    et = ET.parse("test.rss")
    root = et.getroot()
    for channel in root:
        for child in channel:
            if child.tag == "item":
                titlu = ""
                descriere = ""
                for chestie in child:
                    if chestie.tag == "title":
                        titlu = chestie.text
                    if chestie.tag == "description":
                        descriere = chestie.text

                if titlu != "" and descriere != "":
                    db.add(Entry(titlu, descriere))
    db.search("the")
