import asyncio
from concurrent import futures


async def multiply(n, dictionar):
    x = dictionar.keys()
    for i in range(0, len(x) - 1):
        dictionar[x[i]] = dictionar[x[i]] * n
    return dictionar


async def sum2by2(dictionar):
    x = dictionar.keys()
    for i in range(0, len(x) - 1):
        dictionar[x[i]] = dictionar[x[i]] + dictionar[x[i + 1]]
    return dictionar


async def zerorize(dictionar):
    x = dictionar.keys()
    for i in range(0, len(x)):
        if dictionar[x[i]] % 2 == 0:
            dictionar[x[i]] = 0
    return dictionar


async def main():
    dictionar = {3: 6, 5: 8, 8: 7, 2: 9}
    L = await asyncio.gather(
        multiply(3, dictionar),
        sum2by2(dictionar),
        zerorize(dictionar)
    )

    print(L)
    print(dictionar)


asyncio.run(main())
