import os
import sys
from PyQt5.QtWidgets import QWidget, QApplication
from PyQt5.uic import loadUi
from PyQt5 import QtCore


def debug_trace(ui=None):
    from pdb import set_trace
    QtCore.pyqtRemoveInputHook()
    set_trace()
    QtCore.pyqtRestoreInputHook()


class HTMLConverter(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self):
        super(HTMLConverter, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, 'X_si_O.ui')
        loadUi(ui_path, self)

        self.pushButton.clicked.connect(self.move)
        self.pushButton_2.clicked.connect(self.move)
        self.pushButton_3.clicked.connect(self.move)
        self.pushButton_4.clicked.connect(self.move)
        self.pushButton_5.clicked.connect(self.move)
        self.pushButton_6.clicked.connect(self.move)
        self.pushButton_7.clicked.connect(self.move)
        self.pushButton_8.clicked.connect(self.move)
        self.pushButton_9.clicked.connect(self.move)

        self.nrMoves = 0
        self.curent_piece = "X"

    def move(self):
        button = self.sender()
        if self.nrMoves < 8:
            if button.text() == ' ':
                button.setText(self.curent_piece)
                if self.nrMoves % 2 != 0:
                    self.curent_piece = "X"
                else:
                    self.curent_piece = "O"
                self.nrMoves += 1
        else:
            self.nrMoves = 0
            self.setStart()

    def setStart(self):
        self.pushButton.setText(' ')
        self.pushButton_2.setText(' ')
        self.pushButton_3.setText(' ')
        self.pushButton_4.setText(' ')
        self.pushButton_5.setText(' ')
        self.pushButton_6.setText(' ')
        self.pushButton_7.setText(' ')
        self.pushButton_8.setText(' ')
        self.pushButton_9.setText(' ')

    def checkLine(self):
        print()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = HTMLConverter()
    window.show()
    sys.exit(app.exec_())
