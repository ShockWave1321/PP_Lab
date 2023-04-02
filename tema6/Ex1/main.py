import os
import chardet
import lxml.etree as ET


def checkXML(content):
    try:
        ET.fromstring(content)
        return True
    except ET.XMLSyntaxError:
        return False


def checkBMP(content):
    return content[0:1] == b'BM'


def checkBinary(content):
    return chardet.detect(content)['encoding'] is None


def checkUTF16(content):
    return chardet.detect(content)['encoding'] == 'utf-16'


def checkUTF8(content):
    return chardet.detect(content)['encoding'] == 'utf-8'


def checkUTF8_2(content):
    freq1 = 0
    freq2 = 0
    freq3 = 0
    for char in content:
        if char in [9, 10, 13, 32 - 127]:
            freq1 += 1
        elif char in [0 - 8, 11, 12, 14 - 31, 128 - 255]:
            freq2 += 1
        freq3 += 1
    return freq3 * 0.3 < freq1 and freq3 * 0.05 > freq2


def checkUTF16_2(content):
    freq1 = 0
    freq2 = 0
    for char in content:
        if char == 0:
            freq1 += 1
        freq2 += 1
    return freq1 > freq2 * 0.3


class GenericFile:
    def get_path(self):
        pass

    def get_freq(self):
        pass


class TextASCII(GenericFile):
    path_absolut = ""
    frecvente = {}

    def __init__(self, path_absolut, content):
        self.freq = {}
        self.path_absolut = path_absolut
        for char in content:
            if char in self.freq.keys():
                self.freq[char] += 1
            else:
                self.freq[char] = 1

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.freq


class TextUNICODE(GenericFile):
    path_absolut = ""
    frecvente = {}

    def __init__(self, path_absolut, content):
        self.freq = {}
        self.path_absolut = path_absolut
        for char in content:
            if char in self.freq.keys():
                self.freq[char] += 1
            else:
                self.freq[char] = 1

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.freq


class Binary(GenericFile):
    path_absolut = ""
    frecvente = {}

    def __init__(self, path_absolut, content):
        self.freq = {}
        self.path_absolut = path_absolut
        for char in content:
            if char in self.freq.keys():
                self.freq[char] += 1
            else:
                self.freq[char] = 1

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.freq


class XMLFile(TextASCII):
    first_tag = ""

    def __init__(self, path_absolut, content):
        super().__init__(path_absolut, content)
        self.first_tag = ET.fromstring(content)

    def get_first_tag(self):
        return self.first_tag


class BMP(Binary):
    width = 0
    heigth = 0
    bpp = 0

    def __init__(self, path_absolut, content):
        super().__init__(path_absolut, content)
        header = content[0:53]
        self.width = int.from_bytes(header[18:22], byteorder='little', signed=False)
        self.height = int.from_bytes(header[22:26], byteorder='little', signed=False)
        self.bpp = int.from_bytes(header[28:30], byteorder='little', signed=False)

    def show_info(self):
        print("Path: " + str(self.path_absolut) + " Height: " + str(self.height) + " Width: " + str(self.width) + " Bpp: " + str(self.bpp))


if __name__ == '__main__':

    asciiFile = []
    unicodeFile = []
    binaryFile = []
    xmlFile = []
    bmpFile = []

    for root, subdirs, files in os.walk("Fisiere"):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)
            if os.path.isfile(file_path):
                f = open(file_path, 'rb')
                try:
                    content = f.read()
                    if checkUTF8(content):
                        asciiFile.append(TextASCII(file_path, content))
                    elif checkUTF16(content):
                        unicodeFile.append(TextUNICODE(file_path, content))
                    elif checkBinary(content):
                        binaryFile.append(Binary(file_path, content))
                    elif checkXML(content):
                        xmlFile.append(XMLFile(file_path, content))
                    elif checkBMP(content):
                        bmpFile.append(BMP(file_path, content))
                finally:
                    f.close()

    print("Fisiere ascii:")
    for fl in asciiFile:
        print(fl.get_path())
    print("Fisiere unicode:")
    for fl in unicodeFile:
        print(fl.get_path())
    print("Fisiere binary:")
    for fl in binaryFile:
        print(fl.get_path())
    print("Fisiere xml:")
    for fl in xmlFile:
        print(fl.get_path())
    print("Fisiere bmp:")
    for fl in bmpFile:
        print(fl.get_path())
