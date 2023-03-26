import sysv_ipc
import os
import sys
from PyQt5.QtWidgets import QWidget, QApplication, QFileDialog
from PyQt5.uic import loadUi
from PyQt5 import QtCore


def toHTML(text):
    html = ""
    text = text.split('\n')
    html += f'<html>\n' \
            f'    <head>\n' \
            f'        <title>{text[0]}</title>\n' \
            f'    </head>\n' \
            f'    <body>\n'
    for word in text[1:]:
        if word != "":
            html += f'        <p>{word}</p>\n'
    html += f'    </body>\n' \
            f'</html>'
    return html


def debug_trace(ui=None):
    from pdb import set_trace
    QtCore.pyqtRemoveInputHook()
    set_trace()
    QtCore.pyqtRestoreInputHook()


class HTMLConverter(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self):
        super(HTMLConverter, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, 'html_converter1.ui')
        loadUi(ui_path, self)
        self.browse_btn.clicked.connect(self.browse)
        self.convertHTML_btn.clicked.connect(self.convertHTML)
        self.send_C_btn.clicked.connect(self.sendC)
        self.file_path = None
        self.text = ""

    def browse(self):
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file, _ = QFileDialog.getOpenFileName(self,
                                              caption='Select file',
                                              directory='',
                                              filter="Text Files (*.txt)",
                                              options=options)
        if file:
            self.file_path = file
            self.path_line_edit.setText(file)

    def convertHTML(self):
        if self.file_path:
            with open(self.file_path, 'r') as file:
                line = file.readline()
                while line:
                    self.text += line
                    line = file.readline()
                self.text = toHTML(self.text)
                self.html_result.setPlainText(self.text)
        else:
            self.html_result.setPlainText('Invalid path')

    def sendC(self):
        message_queue = sysv_ipc.MessageQueue(-1)
        message_queue.send(self.text)


if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = HTMLConverter()
    window.show()
    sys.exit(app.exec_())
