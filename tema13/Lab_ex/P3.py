def Zip(*iterables):
    return list(map(lambda *args: args, *iterables))


if __name__ == '__main__':
    list1 = [1, 3, 5]
    list2 = [2, 4, 6]
    zipped = Zip(list1, list2)
    print(zipped)
