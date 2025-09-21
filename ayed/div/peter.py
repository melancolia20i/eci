import sys

def main():
    datos = sys.stdin.read().strip().split()
    pos = 0
    salida = []
    while pos < len(datos):
        cantidad = int(datos[pos]); pos += 1
        precios = []
        for _ in range(cantidad):
            precios.append(int(datos[pos])); pos += 1
        presupuesto = int(datos[pos]); pos += 1

        precios.sort()
        mejor_dif = precios[-1]
        libro1 = libro2 = 0
        for i in range(cantidad - 1):
            for j in range(i + 1, cantidad):
                if precios[i] + precios[j] == presupuesto and precios[j] - precios[i] < mejor_dif:
                    libro1, libro2 = precios[i], precios[j]
                    mejor_dif = precios[j] - precios[i]

        print(f"Peter should buy books whose prices are {libro1} and {libro2}.\n")

if __name__ == "__main__":
    main()

