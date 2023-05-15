from threading import Thread, Condition
import time
elemente = []
conditie = Condition()


class Consumator(Thread):
    def __init__(self):
        Thread.__init__(self)

    def consumator(self):
        global conditie
        global elemente
        conditie.acquire()
        if len(elemente) == 0:
            conditie.wait()
            print('mesaj de la consumator: nu am nimic disponibil')
        elemente.pop()
        print('mesaj de la consumator : am utlizat un element')
        print('mesaj de la consumator : mai am disponibil', len(elemente),
              'elemente')
        conditie.notify()
        conditie.release()

    def run(self):
        for i in range(5):
            self.consumator()


class Producator(Thread):
    def __init__(self):
        Thread.__init__(self)

    def producator(self):
        global conditie
        global elemente
        conditie.acquire()
        if len(elemente) == 10:
            conditie.wait()
            print('mesaj de la producator : am disponibile', len(elemente),
                  'elemente')
            print('mesaj de la producator : am oprit productia')
        elemente.append(1)
        print('mesaj de la producator : am produs', len(elemente), 'elemente')
        conditie.notify()
        conditie.release()

    def run(self):
        for i in range(5):
            self.producator()


if __name__ == '__main__':
    producator = Producator()
    consumator = Consumator()
    producator.start()
    consumator.start()
    producator.join()
    consumator.join()
