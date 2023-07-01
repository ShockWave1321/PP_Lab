import itertools as it
import os


def generare_temporara(s1, s2):
    for tmp in map(lambda idx: f'{s1}-{idx}-{s2}.tmp', it.count(10)):
        yield tmp


if __name__ == '__main__':
    generator = generare_temporara("mama", "tata")
    for i in range(0, 3):
        open(next(generator), 'w')
