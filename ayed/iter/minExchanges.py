from sys import stdin

def sort (arr):
    flips = 0
    n = len(arr)
    for i in range(n):
        for j in range(n - 1 - i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                flips += 1
    return flips

def main():
    lines = stdin.read().splitlines()

    for i in range(0, len(lines), 2):
        length = int(lines[i])
        lista  = list(map(int, lines[i + 1].split(' ')))
        flips  = sort(lista)
        print(f"Minimum exchange operations : {flips}")

main()
