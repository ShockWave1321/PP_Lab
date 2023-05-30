import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time
from random import seed
from random import randint

seed(1)

n = 12000
Arr = [randint(0, n) for _ in range(n)]

N = 10000
Set = set()
while len(Set) < N:
    Set.add(randint(n, 10 * n))

aux = []


def inc(aux):
    with threading.Lock():
        for i in aux:
            i += 1


def sort(aux):
    with threading.Lock():
        for i in range(0, len(aux)):
            for j in range(i, len(aux)):
                if aux[i] > aux[j]:
                    aux[i], aux[j] = aux[j], aux[i]


def prime_check(num):
    if num <= 1:
        return 0
    if num == 2:
        return 1
    elif num > 1:
        for i in range(2, num):
            if (num % i) == 0:
                return 0
    return 1


def primes():
    setC = set()
    with threading.Lock():
        for i in Set:
            if prime_check(i) != 1:
                setC.add(i)


def countdown():
    primes()


def ver_1(collection):
    global aux
    aux = collection.copy()
    thread_1 = threading.Thread(target=countdown)
    thread_2 = threading.Thread(target=countdown)
    thread_1.start()
    thread_2.start()
    thread_1.join()
    thread_2.join()


def ver_2(collection):
    global aux
    aux = collection.copy()
    countdown()
    countdown()


def ver_3(collection):
    global aux
    aux = collection.copy()
    process_1 = multiprocessing.Process(target=countdown)
    process_2 = multiprocessing.Process(target=countdown)
    process_1.start()
    process_2.start()
    process_1.join()
    process_2.join()


def ver_4(collection):
    global aux
    aux = collection.copy()
    with ThreadPoolExecutor(max_workers=2) as executor:
        executor.submit(countdown)
        executor.submit(countdown)


def ver_1_sort(collection):
    global aux
    aux = collection.copy()
    thread_1 = threading.Thread(target=sort, args=[aux[:len(aux)//2]])
    thread_2 = threading.Thread(target=sort, args=[aux[len(aux)//2+1:]])
    thread_1.start()
    thread_2.start()
    thread_1.join()
    thread_2.join()


def ver_2_sort(collection):
    global aux
    aux = collection.copy()
    sort(aux[:len(aux)//2])
    sort(aux[len(aux)//2+1:])


def ver_3_sort(collection):
    global aux
    aux = collection.copy()
    process_1 = multiprocessing.Process(target=sort, args=[aux[:len(aux)//2]])
    process_2 = multiprocessing.Process(target=sort, args=[aux[len(aux)//2+1:]])
    process_1.start()
    process_2.start()
    process_1.join()
    process_2.join()


def ver_4_sort(collection):
    global aux
    aux = collection.copy()
    with ThreadPoolExecutor(max_workers=2) as executor:
        executor.submit(sort, aux[:len(aux)//2])
        executor.submit(sort, aux[len(aux)//2+1:])


def ver_1_inc(collection):
    global aux
    aux = collection.copy()
    thread_1 = threading.Thread(target=inc, args=[aux[:len(aux)//2]])
    thread_2 = threading.Thread(target=inc, args=[aux[len(aux)//2+1:]])
    thread_1.start()
    thread_2.start()
    thread_1.join()
    thread_2.join()


def ver_2_inc(collection):
    global aux
    aux = collection.copy()
    inc(aux[:len(aux)//2])
    inc(aux[len(aux)//2+1:])


def ver_3_inc(collection):
    global aux
    aux = collection.copy()
    process_1 = multiprocessing.Process(target=inc, args=[aux[:len(aux)//2]])
    process_2 = multiprocessing.Process(target=inc, args=[aux[len(aux)//2+1:]])
    process_1.start()
    process_2.start()
    process_1.join()
    process_2.join()


def ver_4_inc(collection):
    global aux
    aux = collection.copy()
    with ThreadPoolExecutor(max_workers=2) as executor:
        executor.submit(inc, aux[:len(aux)//2])
        executor.submit(inc, aux[len(aux)//2+1:])


if __name__ == '__main__':
    start = time.time()
    ver_1(Arr)
    end = time.time()
    print("\n Timp executie pseudoparalelism cu GIL")
    print(end - start)

    start = time.time()
    ver_2(Arr)
    end = time.time()
    print("\n Timp executie secvential")
    print(end - start)

    start = time.time()
    ver_3(Arr)
    end = time.time()
    print("\n Timp executie paralela cu multiprocessing")
    print(end - start)

    start = time.time()
    ver_4(Arr)
    end = time.time()
    print("\n Timp executie paralela cu concurrent.futures")
    print(end - start)

    start = time.time()
    ver_1_sort(Arr)
    end = time.time()
    print("\n Timp sortare pseudoparalelism cu GIL")
    print(end - start)

    start = time.time()
    ver_2_sort(Arr)
    end = time.time()
    print("\n Timp sortare secvential")
    print(end - start)

    start = time.time()
    ver_3_sort(Arr)
    end = time.time()
    print("\n Timp sortare paralela cu multiprocessing")
    print(end - start)

    start = time.time()
    ver_4_sort(Arr)
    end = time.time()
    print("\n Timp sortare paralela cu concurrent.futures")
    print(end - start)

    Arr = [0 for _ in range(0, n)]

    start = time.time()
    ver_1_inc(Arr)
    end = time.time()
    print("\n Timp incrementare pseudoparalelism cu GIL")
    print(end - start)

    start = time.time()
    ver_2_inc(Arr)
    end = time.time()
    print("\n Timp incrementare secvential")
    print(end - start)

    start = time.time()
    ver_3_inc(Arr)
    end = time.time()
    print("\n Timp incrementare paralela cu multiprocessing")
    print(end - start)

    start = time.time()
    ver_4_inc(Arr)
    end = time.time()
    print("\n Timp incrementare paralela cu concurrent.futures")
    print(end - start)


