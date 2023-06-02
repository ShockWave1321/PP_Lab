def is_prime(num):
    if num < 2:
        return False
    if num % 2 == 0:
        return False
    for i in range(3, int(num ** 0.5) + 1, 2):
        if num % i == 0:
            return False
    return True


List = [31, 23, 453, 531, 53, 2, 4, 5, 643, 28, 43, 54, 64]
trans1 = filter(lambda x: is_prime(x), List)
#trans2 = filter(lambda x: x % 2 != 0, trans1)
finalTrans = list(filter(lambda x: x <= 50, trans1))
print(finalTrans)
