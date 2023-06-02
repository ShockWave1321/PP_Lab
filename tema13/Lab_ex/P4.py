if __name__ == '__main__':

    n = int(input("n: "))
    even = filter(lambda x: x % 2 == 0, range(1, n+1))
    PF = (x for x in even if int(x ** 0.5) ** 2 == x)
    print(list(PF))
