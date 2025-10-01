import sys

class Queue:
    def __init__(self):
        self.data = []
        self.front = 0

    def enqueue(self, item):
        self.data.append(item)

    def dequeue(self):
        if self.is_empty():
            return
        return self.data.pop(0)

    def is_empty(self):
        return self.front >= len(self.data)


def main():
    datos = sys.stdin.read().strip().split()
    t = int(datos[0])
    pos = 1

    for caso in range(t):
        largo = int(datos[pos]) * 100
        pos += 1

        autos = []
        while True:
            v = int(datos[pos])
            pos += 1
            if v == 0:
                break
            autos.append(v)

        n = len(autos)

        cola = Queue()
        cola.enqueue((0, largo, largo))

        predecesor = {}
        lado = {}

        max_autos = 0
        ultimo_estado = None

        while not cola.is_empty():
            idx, izq, der = cola.dequeue()

            if idx > max_autos:
                max_autos = idx
                ultimo_estado = (idx, izq)

            if idx == n:
                continue

            if izq >= autos[idx]:
                nuevo = (idx + 1, izq - autos[idx], der)
                clave = (idx + 1, izq - autos[idx])
                if clave not in predecesor:
                    predecesor[clave] = (idx, izq)
                    lado[clave] = "port"
                    cola.enqueue(nuevo)

            if der >= autos[idx]:
                nuevo = (idx + 1, izq, der - autos[idx])
                clave = (idx + 1, izq)
                if clave not in predecesor:
                    predecesor[clave] = (idx, izq)
                    lado[clave] = "starboard"
                    cola.enqueue(nuevo)

        print(max_autos)

        camino = []
        estado = ultimo_estado
        while estado in predecesor:
            camino.append(lado[estado])
            estado = predecesor[estado]

        for d in reversed(camino):
            print(d)

        if caso != t - 1:
            print()


if __name__ == "__main__":
    main()

