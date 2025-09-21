from sys import stdin

def main():
    n = int(stdin.readline().strip())
    for i in range(n):
        n_inst = int(stdin.readline().strip())
        instrucciones = []
        x_pos = 0

        for j in range(n_inst):
            ins = stdin.readline().strip()
            if ins == "LEFT":
                instrucciones.append(-1)
                x_pos -= 1
            elif ins == "RIGHT":
                instrucciones.append(1)
                x_pos += 1
            else:
                corte = ins.split()
                indice = int(corte[2])
                accion = instrucciones[indice - 1]
                instrucciones.append(accion)
                x_pos += accion
        print(x_pos)
main()
