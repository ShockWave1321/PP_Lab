import threading
import multiprocessing
from concurrent.futures import ThreadPoolExecutor
import time
from random import seed
from random import randint

seed(1)

n = 12000
N = 10000
Arr = [randint(0, n) for _ in range(n)]
Set = set()
while len(Set) < N:
    Set.add(randint(n, 10*n))


def sort():
    arrCopy = Arr
    for i in range(0,n):
        for j in range(i,n):
            if arrCopy[i] > arrCopy[j]:
                aux = arrCopy[i]
                arrCopy[i] = arrCopy[j]
                arrCopy[j] = aux

def prime_check(num):
    if num == 1:
        return 0
    elif num > 1:
        for i in range(2, num):
            if (num % i) == 0:
               return 0
    return 1

def primes():
    vect = Set
    for i in vect:
        if prime_check(i) == 1:
            pass

def countdown():
    primes()

def ver_1():
    thread_1 = threading.Thread(target=countdown,args=[])
    thread_2 = threading.Thread(target=countdown)
    thread_1.start()
    thread_2.start()
    rez1 = thread_1.join()
    thread_2.join()

def ver_2():
    countdown()
    countdown()

def ver_3():
    process_1 = multiprocessing.Process(target=countdown)
    process_2 = multiprocessing.Process(target=countdown)
    process_1.start()
    process_2.start()
    process_1.join()
    process_2.join()

def ver_4():
    with ThreadPoolExecutor(max_workers=2) as executor:
        future = executor.submit(countdown())
        future = executor.submit(countdown())

if __name__ == '__main__':
    start = time.time()
    ver_1()
    end = time.time()
    print("\n Timp executie pseudoparalelism cu GIL")
    print(end - start)
    start = time.time()
    ver_2()
    end = time.time()
    print("\n Timp executie secvential")
    print(end - start)
    start = time.time()
    ver_3()
    end = time.time()
    print("\n Timp executie paralela cu multiprocessing")
    print(end - start)
    start = time.time()
    ver_4()
    end = time.time()
    print("\n Timp executie paralela cu concurrent.futures")
    print(end - start)
