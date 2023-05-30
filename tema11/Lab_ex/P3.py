import multiprocessing

Arr = [5, 2, 3, 1, 4, 6]

arr = multiprocessing.Array('i', Arr)
lock = multiprocessing.Lock()


def multiply(alpha):
    global arr
    with lock:
        for i in range(len(arr)):
            arr[i] *= alpha


def sort():
    global arr
    with lock:
        for i in range(len(arr)):
            for j in range(i, len(arr)):
                if arr[i] > arr[j]:
                    arr[i], arr[j] = arr[j], arr[i]


def display():
    global arr
    with lock:
        for i in arr:
            print(i, end=" ")


if __name__ == '__main__':

    p1 = multiprocessing.Process(target=multiply, args=[5])
    p2 = multiprocessing.Process(target=sort)
    p3 = multiprocessing.Process(target=display)

    p1.start()
    p2.start()
    p3.start()

    p1.join()
    p2.join()
    p3.join()
