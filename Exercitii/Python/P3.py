from tkinter import *
from tkinter import ttk


class Employee:
    def __init__(self, nume, data_nasterii, functie, departament, companie):
        self.nume = nume
        self.data_nasterii = data_nasterii
        self.functie = functie
        self.departament = departament
        self.companie = companie

    def __str__(self):
        return f'\nnume: {self.nume}' \
               f'\ndata nasterii {self.data_nasterii} ' \
               f'\nfunctie {self.functie}' \
               f'\ncompanie {self.companie}' \
               f'\ndepartament {self.departament}'


angajat1 = Employee(
    nume="Eugen Coilung",
    data_nasterii="4.06.2002",
    functie="smecher",
    departament="smecherie",
    companie="COntinental"
)


def addEmployee(employee, angajati, mainframe):
    angajati.append(employee)
    ttk.Label(mainframe, text=str(employee)).grid(column=2, row=len(angajati) + 11, sticky=EW)


if __name__ == '__main__':
    root = Tk()
    root.title = "Please add an employee"

    mainframe = ttk.Frame(root, padding="5 5 12 12")
    mainframe.grid(column=0, row=0, sticky=(N, W, E, S))
    root.columnconfigure(0, weight=1)
    root.rowconfigure(0, weight=1)

    NameInput = StringVar()
    NameInput_entry = ttk.Entry(mainframe, width=20, textvariable=NameInput)
    NameInput_entry.grid(column=2, row=2, sticky=(W, E))

    DayOfBirthInput = StringVar()
    DayOfBirth_entry = ttk.Entry(mainframe, width=20, textvariable=DayOfBirthInput)
    DayOfBirth_entry.grid(column=2, row=3, sticky=(W, E))

    MonthOfBirthInput = StringVar()
    months = ttk.Combobox(mainframe, width=27, textvariable=MonthOfBirthInput)
    months['values'] = (' January',
                        ' February',
                        ' March',
                        ' April',
                        ' May',
                        ' June',
                        ' July',
                        ' August',
                        ' September',
                        ' October',
                        ' November',
                        ' December')
    months.grid(column=2, row=4)

    YearOfBirthInput = StringVar()
    YearOfBirth_entry = ttk.Entry(mainframe, width=20, textvariable=YearOfBirthInput)
    YearOfBirth_entry.grid(column=2, row=5, sticky=(W, E))

    FunctieInput = StringVar()
    Functie_entry = ttk.Entry(mainframe, width=20, textvariable=FunctieInput)
    Functie_entry.grid(column=2, row=6, sticky=(W, E))

    DepartamentInput = StringVar()
    Departament_entry = ttk.Entry(mainframe, width=20, textvariable=DepartamentInput)
    Departament_entry.grid(column=2, row=7, sticky=(W, E))

    CompanieInput = StringVar()
    Companie_entry = ttk.Entry(mainframe, width=20, textvariable=CompanieInput)
    Companie_entry.grid(column=2, row=8, sticky=(W, E))

    ttk.Label(mainframe, text='Please add an employee:').grid(column=2, row=0, sticky=N)

    ttk.Label(mainframe, text='').grid(column=1, row=1, sticky=N)
    ttk.Label(mainframe, text='Name: ').grid(column=1, row=2, sticky=E)
    ttk.Label(mainframe, text='Day of Birth: ').grid(column=1, row=3, sticky=E)
    ttk.Label(mainframe, text='Month of Birth: ').grid(column=1, row=4, sticky=E)
    ttk.Label(mainframe, text='Year of Birth: ').grid(column=1, row=5, sticky=E)
    ttk.Label(mainframe, text='Functie: ').grid(column=1, row=6, sticky=E)
    ttk.Label(mainframe, text='Departament: ').grid(column=1, row=7, sticky=E)
    ttk.Label(mainframe, text='Companie: ').grid(column=1, row=8, sticky=E)

    angajati = []
    angajati.append(angajat1)

    print(str(angajati))

    ttk.Button(mainframe, text="ADD Employee", command=lambda: addEmployee(
        Employee(
            nume=NameInput.get(),
            data_nasterii=DayOfBirthInput.get() + MonthOfBirthInput.get() + " " + YearOfBirthInput.get(),
            functie=FunctieInput.get(),
            departament=DepartamentInput.get(),
            companie=CompanieInput.get()
        ),
        angajati,
        mainframe
    )
               ).grid(column=3, row=3, sticky=W)

    ttk.Label(mainframe, text='List de angajati: ').grid(column=2, row=10, sticky=N)


    menubar = Menu(root)
    root.config(menu=menubar)

    file_menu = Menu(menubar)
    file_menu.add_command(label='Exit', command=root.destroy)
    menubar.add_cascade(label='File', menu=file_menu, underline=0)

    root.mainloop()