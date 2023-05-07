from abc import ABCMeta, abstractmethod
from copy import copy


class File(metaclass=ABCMeta):
    def __init__(self):
        self.title = None
        self.author = None
        self.paragraphs = []

    @abstractmethod
    def read_file_from_stdin(self):
        pass


class HTMLFile(File):
    def __init__(self):
        super().__init__()

    def read_file_from_stdin(self):
        self.title = input("Titlu: ")
        self.author = input("Autor: ")
        para_num = int(input("Numar de paragrafe: "))
        for i in range(0, para_num):
            print(f"Paragraf {i + 1}:")
            self.paragraphs.append(input())

    def print_html(self):
        print(f"<h1>Title: {self.title}</h1>")
        print(f"<p>Author: {self.author}</p>")
        for paragraph in self.paragraphs:
            print(f"<p>{paragraph}</p>")


class JSONFile(File):
    def __init__(self):
        super().__init__()

    def read_file_from_stdin(self):
        self.title = input("Titlu: ")
        self.author = input("Autor: ")
        para_num = int(input("Numar de paragrafe: "))
        for i in range(0, para_num):
            print(f"Paragraf {i + 1}:")
            self.paragraphs.append(input())

    def print_json(self):
        print("{")
        print(f"\"title\": {self.title},")
        print(f"\"author\": {self.author},")
        print(f"\"paragraphs:\" {self.paragraphs}")
        print("}")


class TextFile(File):
    def __init__(self):
        super().__init__()
        self.template = None

    def read_file_from_stdin(self):
        self.title = input("Titlu: ")
        self.author = input("Autor: ")
        para_num = int(input("Numar de paragrafe: "))
        for i in range(0, para_num):
            print(f"Paragraf {i + 1}:")
            self.paragraphs.append(input())

    def clone(self):
        return copy(self)

    def print_text(self):
        print(f"Template: {self.template}")
        print(f"Titlu: {self.title}")
        print(f"Autor: {self.author}")
        for paragraph in self.paragraphs:
            print(paragraph)


class ArticleTextFile(TextFile):
    def __init__(self):
        super().__init__()
        self.template = "Article"

    def print_text(self):
        print(f"\t\t\t{self.title}")
        print(f"\t\t\t\t\t{self.author}")
        for paragraph in self.paragraphs:
            print(paragraph)


class BlogTextFile(TextFile):
    def __init__(self):
        super().__init__()

    def print_text(self):
        print(f"{self.title}")
        for paragraph in self.paragraphs:
            print(paragraph)
        print(f"\nWritten by {self.author}")


class FileFactory:
    @staticmethod
    def factory(file_type):
        if file_type == "HTMLFile":
            return HTMLFile()
        elif file_type == "JSONFile":
            return JSONFile()
        elif file_type == "TextFile":
            return TextFile()


if __name__ == "__main__":
    Factory = FileFactory
    HTMLFile = Factory.factory("HTMLFile")
    HTMLFile.read_file_from_stdin()
    HTMLFile.print_html()
    JSONFile = Factory.factory("JSONFile")
    JSONFile.read_file_from_stdin()
    JSONFile.print_json()
    TextFile = Factory.factory("TextFile")
    TextFile.read_file_from_stdin()
    TextFile.print_text()
    ArticleTextFile = TextFile.clone()
    ArticleTextFile.print_text()
