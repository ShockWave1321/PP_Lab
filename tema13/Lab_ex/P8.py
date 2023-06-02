def is_prime(num):
    if num < 2:
        return False
    if num % 2 == 0:
        return False
    for i in range(3, int(num ** 0.5) + 1, 2):
        if num % i == 0:
            return False
    return True


class Automat:
    def __init__(self, numbers):
        self.numbers = numbers
        self.state = 0

    def run(self):
        while self.state != 3:
            if self.state == 0:
                self.numbers = list(filter(lambda x: is_prime(x), self.numbers))
                if self.numbers:
                    self.state = 1
                else:
                    self.state = 3
            elif self.state == 1:
                self.numbers = list(filter(lambda x: x % 2 != 0, self.numbers))
                if self.numbers:
                    self.state = 2
                else:
                    self.state = 3
            elif self.state == 2:
                self.numbers = list(filter(lambda x: x <= 50, self.numbers))
                if self.numbers:
                    self.state = 3


List = [31, 23, 453, 531, 53, 2, 4, 5, 643, 28, 43, 54, 64]
sm = Automat(List)
sm.run()
print(sm.numbers)
