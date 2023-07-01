import asyncio


async def multiply(n, dictionary):
    for key in dictionary:
        dictionary[key] *= n
    return dictionary


async def sum2by2(dictionary):
    keys = list(dictionary.keys())
    for i in range(len(keys) - 1):
        dictionary[keys[i]] += dictionary[keys[i + 1]]
    return dictionary


async def zerorize(dictionary):
    keys = list(dictionary.keys())
    for key in keys:
        if dictionary[key] % 2 == 0:
            dictionary[key] = 0
    return dictionary


async def main():
    dictionary = {3: 6, 5: 8, 8: 7, 2: 9}

    dictionary = await multiply(3, dictionary)
    dictionary = await sum2by2(dictionary)
    dictionary = await zerorize(dictionary)

    print(dictionary)


asyncio.run(main())
