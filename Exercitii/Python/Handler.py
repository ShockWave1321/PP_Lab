from abc import ABC, abstractmethod


class Handler(ABC):
    @abstractmethod
    def handleRequest(self, forword, message):
        pass


class Paznici(Handler):
    def __init__(self, next: Handler):
        self.next = next

    def handleRequest(self, messageLevel, message):
        if messageLevel >= 5:
            print(f'Paznicul va rezolva {message}')
        else:
            print(f'\nDublez si dau mai departe la Politie')
            self.next.handleRequest(messageLevel, message)


class Politie(Handler):
    def __init__(self, next: Handler):
        self.next = next

    def handleRequest(self, messageLevel, message):
        if messageLevel == 4:
            print(f'Politia va rezolva {message}')
        else:
            print(f'\nDublez si dau mai departe la SRI')
            self.next.handleRequest(messageLevel, message)


class SRI(Handler):
    def __init__(self, next: Handler):
        self.next = next

    def handleRequest(self, messageLevel, message):
        if messageLevel == 3:
            print(f'SRI-ul va rezolva {message}')
        else:
            print(f'\nDublez si dau mai departe la SIE')
            self.next.handleRequest(messageLevel, message)


class SIE(Handler):
    def __init__(self, next: Handler):
        self.next = next

    def handleRequest(self, messageLevel, message):
        if messageLevel == 2:
            print(f'SRI-ul va rezolva {message}')
        else:
            print(f'\nDublez si dau mai departe la CSAT')
            self.next.handleRequest(messageLevel, message)


class CSAT(Handler):
    def __init__(self, next: Handler):
        self.next = next

    def handleRequest(self, messageLevel, message):
        if messageLevel == 1:
            print(f'CSAT-ul va rezolva {message}')
        else:
            print(f'\nDublez si dau mai departe la NATO')
            self.next.handleRequest(messageLevel, message)


class NATO(Handler):
    def handleRequest(self, messageLevel, message):
        if messageLevel <= 0:
            print(f'NATO-ul va rezolva {message}')


if __name__ == "__main__":
    nato = NATO()
    csat = CSAT(nato)
    sie = SIE(csat)
    sri = SRI(sie)
    politie = Politie(sri)
    paznici = Paznici(politie)

    paznici.handleRequest(0, "hacuim o baba")
