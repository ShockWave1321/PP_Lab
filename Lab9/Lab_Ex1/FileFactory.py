from File import File
from HTMLFile import HTMLFile


class FileFactory():
    def factory(self, file_type):
        File.read_file_from_stdin()
        if file_type == "HTMLFile":
            return HTMLFile()