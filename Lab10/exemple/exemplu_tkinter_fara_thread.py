from tkinter import Tk, Button, TOP
from tkinter import ttk
import time


TIME_LIMIT = 100


class ProgressBarExample:
    def __init__(self, master):
        self.master = master
        self.startBtn = Button(self.master, command=self.onStartButtonClick)
        self.startBtn.configure(
            text="Start", background="Grey",
            padx=50)
        self.master.geometry("300x100")
        self.startBtn.pack(side=TOP)

    def progress(self):
        self.progressBar = ttk.Progressbar(
            self.master, orient="horizontal",
            length=200, mode="indeterminate")
        self.progressBar.pack(side=TOP)

    def onStartButtonClick(self):
        self.progress()
        self.progressBar.start()
        self.counter()
        self.progressBar.stop()

    def counter(self):
        count = 0
        while count < TIME_LIMIT:
            count += 1
            time.sleep(0.1)
            self.progressBar['value'] = count


if __name__ == '__main__':
    root = Tk()
    root.title("Test Button")
    main_ui = ProgressBarExample(root)
    root.mainloop()
