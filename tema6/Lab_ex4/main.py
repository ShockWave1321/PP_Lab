class Celula:
    def get_nume(self):
        pass


class FibraMusculara(Celula):
    nume = ""
    masa_musculara = 0

    def __init__(self, nume, masa_musculara):
        self.nume = nume
        self.masa_musculara = masa_musculara

    def get_nume(self):
        return self.nume

    def get_masa_musculara(self):
        return self.masa_musculara


class MuschiGenetic:
    fibre = []
    nume = ""
    masa_musculara = 0
    scop = ""

    def __init__(self, nume, fibre_musculare, scop):
        self.nume = nume
        self.fibre = fibre_musculare
        self.scop = scop
        for fibra in fibre_musculare:
            self.masa_musculara += fibra.get_masa_musculara()

    def get_nume(self):
        return self.nume

    def get_masa_musculara(self):
        return self.masa_musculara

    def get_scop(self):
        return self.scop


class MuschiMembru(MuschiGenetic):
    membru = ""
    functie = ""

    def __init__(self, membru, muschi: MuschiGenetic, functie):
        super().__init__(muschi.get_nume(), muschi.fibre, muschi.get_scop())
        self.membru = "Membru " + membru
        self.functie = functie

    def get_membru(self):
        return self.membru

    def get_functie(self):
        return self.functie


class FibraNervoasa(Celula):
    nume = ""
    lungime = 0

    def __init__(self, nume, lungime):
        self.nume = nume
        self.lungime = lungime

    def get_nume(self):
        return self.nume

    def get_lungime(self):
        return self.lungime


class TrunchiNervos:
    nervi = []
    nume = ""
    lungime = 0
    specializare = ""

    def __init__(self, nume, nervi, specializare):
        self.nume = nume
        self.nervi += nervi
        for n in nervi:
            self.lungime += n.get_lungime()
        self.specializare = specializare

    def get_nume(self):
        return self.nume

    def get_lungime(self):
        return self.lungime

    def get_specializare(self):
        return self.specializare


if __name__ == '__main__':
    fibra1 = FibraMusculara("Fibra1", 0.40)
    fibra2 = FibraMusculara("Fibra2", 0.45)
    fibra3 = FibraMusculara("Fibra3", 0.5)

    muschi_genetic1 = MuschiGenetic("Muschi1", [fibra2, fibra3], "locomotor")
    muschi_genetic2 = MuschiGenetic("Muschi2", [fibra1, fibra3], "locomotor")
    print(f'muschi1 - {muschi_genetic1.get_masa_musculara()}')

    muschi_biceps_stang = MuschiMembru("superior stang", muschi_genetic2, "incordare brat")
    muschi_biceps_drept = MuschiMembru("superior drept", muschi_genetic1, "incordare drept")
    print(f'muschi biceps stang - {muschi_biceps_stang.get_masa_musculara()}')

    masa_totala_muschi = 0
    for muschi in [muschi_biceps_stang, muschi_biceps_drept]:
        masa_totala_muschi += muschi.get_masa_musculara()
    print(f'Masa totala: {masa_totala_muschi}')

    for muschi in [muschi_biceps_stang, muschi_biceps_drept]:
        if muschi.get_scop() == "locomotor":
            print(muschi.get_membru())
