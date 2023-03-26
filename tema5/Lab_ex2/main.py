import sys
import random
import os
from time import time
from PyQt5.QtWidgets import QApplication, QWidget, QFileDialog, QTextEdit
from PyQt5.uic import loadUi


class Jurnal(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self):
        super(Jurnal, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, 'jurnal.ui')
        loadUi(ui_path, self)

        self.load_btn.clicked.connect(self.loadText)
        self.save_btn.clicked.connect(self.saveText)

        with open('citate.txt', 'r') as f:
            quotes = f.readlines()
        self.quote = random.choice(quotes)
        self.citat.setText(self.quote)

    def loadText(self):
        filename, _ = QFileDialog.getOpenFileName(self, 'Open File')
        if filename:
            with open(filename, 'r') as f:
                self.intrare_jurnal.setPlainText(f.read())

    def saveText(self):
        filename, _ = QFileDialog.getSaveFileName(self, 'Save File', f'{time()}.txt')
        if filename:
            with open(filename, 'w') as f:
                f.write(self.intrare_jurnal.toPlainText())


if __name__ == '__main__':
    app = QApplication(sys.argv)
    jurnal = Jurnal()
    jurnal.show()
    sys.exit(app.exec_())