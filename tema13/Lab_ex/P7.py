from functools import partial
from itertools import starmap

f = lambda x, k: x * x + k


def starmapF(it, k):
    return list(starmap(f, zip(iterator, [k] * len(it))))


def partialF(it, c):
    partial_f = partial(f, k=c)
    return [partial_f(x) for x in it]


if __name__ == '__main__':
    iterator = [1, 3, 9, 4, 7, 10]
    k = 2

    res1 = starmapF(iterator, k)
    res2 = partialF(iterator, k)
    print(res1)
    print(res2)
