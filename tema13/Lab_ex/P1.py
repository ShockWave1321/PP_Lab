def check_prime(original_function):
    def wrapped_f(num):
        num = original_function(num)
        if num < 2:
            return False
        if num % 2 == 0:
            return False
        for i in range(3, int(num ** 0.5) + 1, 2):
            if num % i == 0:
                return False
        return True
    return wrapped_f


class int(int):
    @property
    @check_prime
    def is_prime(self):
        return self


if __name__ == '__main__':
    print(int(127).is_prime)
    print(int(120).is_prime)
