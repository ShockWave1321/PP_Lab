import re


def letters(text):
    return ''.join([c for c in text if c.isalpha() or c == " "])


def main():
    file = open("text.txt", "r")
    string = file.read()
    new_str = string

    new_str = re.sub('\.', '', new_str)
    new_str = re.sub(',', '', new_str)
    new_str = re.sub('!', '', new_str)
    new_str = re.sub('\?', '', new_str)

    print("Optiuni operatii:\n"
          "1 - Stergere spatii multiple\n"
          "2 - Lower case\n"
          "3 - Upper case\n"
          "4 - Filtrare cuvinte cu un anumit nr de litere\n"
          "5 - Filtrare numere\n")

    i = 0
    while i != 2:
        x = int(input("Optiune: "))
        if x == 1:
            new_str = re.sub(' +', ' ', new_str)
        elif x == 2:
            new_str = new_str.lower()
        elif x == 3:
            new_str = new_str.upper()
        elif x == 4:
            n = int(input("Nr. litere cuvinte de filtrat: "))
            temp = new_str.split()
            new_str = list(filter(lambda ele: len(ele) != n or ele == "\n", temp))
            new_str = ' '.join(new_str)
        elif x == 5:
            new_str = letters(new_str)
        i = i + 1
    print(string)
    print(new_str)


if __name__ == '__main__':
    main()
