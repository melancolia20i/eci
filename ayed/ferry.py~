# ferry length E [1, 100]    m
# car length   E [100, 3000] cm

import sys
from collections import deque

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

        cola = deque()
        cola.append((0, largo, largo))

        predecesor = {}
        lado = {}

        max_autos = 0
        ultimo_estado = None

        while cola:
            idx, izq, der = cola.popleft()

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
                    cola.append(nuevo)

            if der >= autos[idx]:
                nuevo = (idx + 1, izq, der - autos[idx])
                clave = (idx + 1, izq)
                if clave not in predecesor:
                    predecesor[clave] = (idx, izq)
                    lado[clave] = "starboard"
                    cola.append(nuevo)

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

