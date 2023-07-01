import threading

# Inițializăm o barieră pentru 3 thread-uri
bariera = threading.Barrier(3)


def task():
    print("Fire de execuție aflată înainte de barieră")
    bariera.wait()  # Așteaptă celelalte thread-uri
    print("Fire de execuție aflată după barieră")


# Creăm și pornim cele 3 thread-uri
thread1 = threading.Thread(target=task)
thread2 = threading.Thread(target=task)
thread3 = threading.Thread(target=task)
thread1.start()
thread2.start()
thread3.start()

# Așteptăm ca toate thread-urile să se termine
thread1.join()
thread2.join()
thread3.join()

# Fire de execuție aflată înainte de barieră
# Fire de execuție aflată înainte de barieră
# Fire de execuție aflată înainte de barieră
# Fire de execuție aflată după barieră
# Fire de execuție aflată după barieră
# Fire de execuție aflată după barieră
