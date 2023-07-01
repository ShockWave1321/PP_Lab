def smart_divide(func):
    def fun(a, b):
        print("I am going to divide", a, "and", b)
        if b == 0:
            print("Whoops! cannot divide")
            return

        return func(a, b)

    return fun


@smart_divide
def divide(a, b):
    print(a / b)


if __name__ == '__main__':
    divide(2, 5)

    divide(2, 0)
