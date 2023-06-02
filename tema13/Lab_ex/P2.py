class str(str):
    @property
    def toPascalCase(self):
        return ''.join([word.capitalize() for word in self.split(" ")])


if __name__ == '__main__':
    print(str("This is not in Pascal").toPascalCase)
