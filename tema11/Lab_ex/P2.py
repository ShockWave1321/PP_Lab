from threading import Thread, Condition
import time


class Buffer:
    def __init__(self):
        self.elemente = []
        self.conditie = Condition()

    def consumator(self):
        self.conditie.acquire()
        if len(self.elemente) == 0:
            print('mesaj de la consumator: nu am nimic disponibil')
            self.conditie.wait()
        self.elemente.pop()
        print('mesaj de la consumator : am utlizat un element')
        print('mesaj de la consumator : mai am disponibil', len(self.elemente), 'elemente')
        self.conditie.notify()
        self.conditie.release()

    def producator(self):
        self.conditie.acquire()
        if len(self.elemente) == 10:
            print('mesaj de la producator : am disponibile', len(self.elemente), 'elemente')
            print('mesaj de la producator : am oprit productia')
            self.conditie.wait()
        self.elemente.append(1)
        print('mesaj de la producator : am produs', len(self.elemente), 'elemente')
        self.conditie.notify()
        self.conditie.release()


class Consumator(Thread):
    def __init__(self, b):
        Thread.__init__(self)
        self.buffer = b

    def run(self):
        for i in range(5):
            self.buffer.consumator()


class Producator(Thread):
    def __init__(self, b):
        Thread.__init__(self)
        self.buffer = b

    def run(self):
        for i in range(5):
            self.buffer.producator()


if __name__ == '__main__':
    buffer = Buffer()
    producator = Producator(buffer)
    consumator = Consumator(buffer)
    producator.start()
    consumator.start()
    producator.join()
    consumator.join()
