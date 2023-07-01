from tkinter import Tk, Canvas, Frame, BOTH


def mysum(n):
    sum = 0
    for i in range(0, n + 1):
        sum += i

    sum += n

    mylist = []
    for i in range(49, 20, -1):
        mylist.append((i, i - sum))

    return (mylist, sum)


def main():
    root = Tk()
    root.geometry("1280x720")
    canvas = Canvas(root, width=1280, height=720, background="white")

    (mylist, sum) = mysum(3)

    i = 0
    j = 0
    while i < len(mylist) - 1:
        j = i + 1

        (xc, yc) = mylist[i]
        (xv, yv) = mylist[j]
        canvas.create_line(xc * 10, yc * 10, xv * 10, yv * 10, fill="black", width=10)

        i = i + 1

    canvas.create_text(100, 100, text=f"sum = {sum}")

    canvas.grid(row=0, column=0)
    root.mainloop()


main()