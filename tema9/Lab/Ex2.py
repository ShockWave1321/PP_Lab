import os


def file_filter(paths):
    for path in paths:
        if os.path.isfile(path):
            yield path


def text_filter(paths):
    for path in paths:
        if path.endswith(".txt"):
            yield path


def line_num(paths):
    for path in paths:
        with open(path, 'r') as file:
            lines = file.readlines()
            line_count = len(lines)
            yield path, line_count


def display_results(lines):
    for path, line_count in lines:
        print(f"{path}: {line_count}")


if __name__ == '__main__':
    file_paths = ["file1.txt", "file2.txt", "file3.txt", "file4.json", "file5"]

    files = file_filter(file_paths)
    txt_files = text_filter(files)
    lines_path_num = line_num(txt_files)
    display_results(lines_path_num)
