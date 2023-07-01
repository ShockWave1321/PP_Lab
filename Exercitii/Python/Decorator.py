import random


class RoomService:
    def __init__(self, price):
        self.price = price

    def printFactura(self):
        print(f'Factura pentru room service este {self.price}')


class Camera:
    def __init__(self, number, price, is_available, room_service: RoomService):
        self.price = price
        self.number = number
        self.is_available = is_available
        self.room_service = room_service

    def is_available(self) -> bool:
        return self.is_available == True

    def make_unavailable(self):
        self.is_available = False

    def call_room_service(self):
        self.price += self.room_service.price

    def decorator_print_factura(func):
        def inner(self):
            roomService.printFactura()
            func(self)

        return inner

    @decorator_print_factura
    def printFactura(self):
        print(f'Camera costa {self.price}')

    def __str__(self):
        availability = "este" if self.is_available else "nu este"
        return f"Camera {self.number} costa {self.price} si {availability} libera."


class Hostel:
    def __init__(self, name, rooms, max):
        self.name = name
        self.rooms = rooms
        self.length = len(rooms)
        self.max = max

    def is_full(self):
        return self.length == self.max


class Person:
    def __init__(self, name, amount_of_money):
        self.name = name
        self.amount_of_money = amount_of_money
        self.camera = None

    def book(self, camera: Camera) -> bool:
        # print(f'pret camera{camera.number} -> {camera.price} ---- bani disponibili {self.amount_of_money}')
        if (camera.is_available == True):
            if camera.price <= self.amount_of_money:
                camera.make_unavailable()
                self.camera = camera
                return True
            else:
                # print(f'{self.name} nu a avut bani de camera{camera.number}')
                pass
        return False

    def call(self):
        self.camera.call_room_service()

    def __str__(self):
        cazata = f'este in camera {self.camera}' if camere == None else f'nu este cazata'
        return f'{self.name} are {self.amount_of_money} lei si {cazata}'


if __name__ == "__main__":
    camere = []
    roomService = RoomService(200)
    for roomNumber in range(10):
        camere.append(Camera(roomNumber, random.randint(500, 1000), 1, roomService))

    persoane = []
    for persoanaNumar in (range(10)):
        persoane.append(Person(f'Persoana{persoanaNumar}', random.randint(500, 1000)))

    for persoana in persoane:
        # print(f'Incearca {persoana.name}')
        for camera in camere:
            if persoana.book(camera) == False:
                # print(f'Camera {camera.number} este deja luata, incarcam alta')
                pass
            else:
                print(f'{persoana.name} a luat camera {camera.number}')
                persoana.call()
                camera.printFactura()
                break
