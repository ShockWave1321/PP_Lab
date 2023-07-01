import pygame
from abc import ABC, abstractmethod


class Figure(ABC):
    @abstractmethod
    def draw(self, surface):
        pass


class FigureFactory(ABC):
    @abstractmethod
    def build(self):
        pass


class Circle(Figure):
    def draw(self, surface):
        pygame.draw.circle(surface, "red", (300, 400), 150)


class CircleFactory(ABC):
    def build(self):
        return Circle()


class Square(Figure):
    def draw(self, surface):
        pygame.draw.rect(surface, "blue", (300, 200, 75, 75))


class SquareFactory(ABC):
    def build(self):
        return Square()


class Rectangle(Figure):
    def draw(self, surface):
        pygame.draw.rect(surface, "pink", (100, 400, 100, 200))


class RectangleFactory(ABC):
    def build(self):
        return Rectangle()


class FactoryFactory:
    def get_factory(self, figura):
        match figura:
            case "circle":
                return CircleFactory()
            case "square":
                return SquareFactory()
            case "rectangle":
                return RectangleFactory()


if __name__ == "__main__":
    pygame.init()
    surface = pygame.display.set_mode((600, 800), pygame.HWSURFACE | pygame.DOUBLEzzzBUF)
    kremlin = FactoryFactory()

    while True:
        figura = kremlin.get_factory(input("Introduceti figura pe care o doriti afisata: ")).build()
        figura.draw(surface)
        for event in pygame.event.get():
            if event.type == pygame.QUIT:
                exit(0)
        pygame.display.flip()
