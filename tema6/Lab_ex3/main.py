import os


class AudioFile:
    def __init__(self, filename):
        if not filename.endswith(self.ext):
            raise Exception("Format nesuportat")
        self.filename = filename

    def play(self):
        pass


class MP3File(AudioFile):
    ext = "mp3"

    def play(self):
        print("se canta {} un mp3".format(self.filename))


class WavFile(AudioFile):
    ext = "wav"

    def play(self):
        print("se canta {} un wav".format(self.filename))


class OggFile(AudioFile):
    ext = "ogg"

    def play(self):
        print("se canta {} un ogg".format(self.filename))


class FlacFile:
    def __init__(self, filename):
        if not filename.endswith(".flac"):
            raise Exception("Format necunoscut")
        self.filename = filename

    def play(self):
        print("se canta {} un flac".format(self.filename))


if __name__ == '__main__':
    # file_path = input('file = ')
    file_path = "AudioFiles/file1.ogg"
    if os.path.isfile(file_path):
        file = os.path.basename(file_path)
        _, extension = os.path.splitext(file)
        if extension == ".ogg":
            OggFile(file).play()
        elif extension == ".mp3":
            MP3File(file).play()
        elif extension == ".wav":
            WavFile(file).play()
        elif extension == ".flac":
            FlacFile(file).play()
        else:
            print("Format necunoscut")
    else:
        print("Nu este fisier")
