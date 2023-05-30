import sys
import time

from PyQt5.QtWidgets import (QApplication, QDialog,
                             QProgressBar, QPushButton)

TIME_LIMIT = 100


class ProgressBarExample(QDialog):
    def __init__(self):
        super().__init__()
        self.initUI()

    def initUI(self):
        self.setWindowTitle('Progress Bar')
        self.progress = QProgressBar(self)
        self.progress.setGeometry(0, 0, 300, 25)
        self.progress.setMaximum(100)
        self.startBtn = QPushButton('Start', self)
        self.startBtn.move(0, 30)
        self.show()

        self.startBtn.clicked.connect(self.onStartButtonClick)

    def onStartButtonClick(self):
        self.startBtn.setEnabled(False)
        count = 0
        while count < TIME_LIMIT:
            count += 1
            time.sleep(0.1)
            self.progress.setValue(count)


if __name__ == "__main__":
    app = QApplication(sys.argv)
    window = ProgressBarExample()
    sys.exit(app.exec_())
