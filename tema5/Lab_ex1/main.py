import os
import tkinter as tk


def check_prime(number):
    if number <= 1:
        return False
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            return False
    return True


class Parser:
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self, gui):
        self.integer_list = None
        self.gui = gui
        self.gui.title('Laborator exercitiu 1')

        self.gui.geometry("640x300")

        self.integer_list_lbl = tk.Label(master=self.gui, text="List of integers:")
        self.integer_list_text = tk.Text(self.gui, width=50, height=1)
        self.integer_list_text.insert(tk.END, str(list(range(1, 16)))[1:-1])

        self.text_field_lbl = tk.Label(master=self.gui, text="Result")
        self.text_field_text = tk.Text(self.gui, width=50, height=10)

        self.add_list_btn = tk.Button(master=self.gui,
                                      text="Add list",
                                      command=self.add_list)
        self.filter_odd_btn = tk.Button(master=self.gui,
                                        text="Filter odd",
                                        command=self.filter_odd)
        self.filter_primes_btn = tk.Button(master=self.gui,
                                           text="Filter primes",
                                           command=self.filter_primes)
        self.sum_numbers_btn = tk.Button(master=self.gui,
                                         text="Sum numbers",
                                         command=self.sum_numbers)

        # alignment on the grid
        self.integer_list_lbl.grid(row=0, column=0)
        self.integer_list_text.grid(row=0, column=1)
        self.add_list_btn.grid(row=0, column=2)
        self.filter_odd_btn.grid(row=1, column=2)
        self.filter_primes_btn.grid(row=2, column=2)
        self.sum_numbers_btn.grid(row=3, column=2)
        self.text_field_lbl.grid(row=1, column=0)
        self.text_field_text.grid(row=1, column=1)

        self.gui.mainloop()

    def add_list(self):
        result = self.integer_list_text.get("1.0", tk.END)
        result = result.strip().replace(' ', '')
        result = [int(item) for item in result.split(',')]
        self.integer_list = result

    def filter_odd(self):
        result = [item for item in self.integer_list if item % 2 == 0]
        self.integer_list = result
        self.text_field_text.delete("1.0", tk.END)
        self.text_field_text.insert(tk.END, result)

    def filter_primes(self):
        result = [item for item in self.integer_list if check_prime(item)]
        self.integer_list = result
        self.text_field_text.delete("1.0", tk.END)
        self.text_field_text.insert(tk.END, result)

    def sum_numbers(self):
        sum = 0
        for item in self.integer_list:
            sum += item
        self.text_field_text.delete("1.0", tk.END)
        self.text_field_text.insert(tk.END, sum)


if __name__ == '__main__':
    root = tk.Tk()
    app = Parser(root)
    root.mainloop()
