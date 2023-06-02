from functional import seq

if __name__ == '__main__':
    s = input("String: ")
    result = seq(list(s))\
        .distinct().\
        reduce(lambda x, y: x+y)
    print(result)

