from functional import seq


if __name__ == '__main__':
    string = input("String: ")
    result = seq(list(string))\
        .group_by(lambda x: x)\
        .map(lambda x: x[0] + str(len(x[1])))\
        .reduce(lambda x, y: x + y)
    print(result)
    #print(seq(list(string)).group_by(lambda x: x))
