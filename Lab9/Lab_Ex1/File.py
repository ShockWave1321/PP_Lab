from abc import ABC,abstractmethod

class File(ABC):
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

    @staticmethod
    def read_file_from_stdin(self):
        title = input("File name:")
        author = input("Author name: ")
        n = int(input("Number of paragraphs: "))
        paragraphs = []
        for i in range(0, n):
            self.paragraphs.append(input("Paragraph: "))
        return File(title, author, paragraphs)


