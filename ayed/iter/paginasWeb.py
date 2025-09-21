from sys import stdin

def main():
    n = int(stdin.readline())

    for i in range(n):
        lista = []
        max_ = 0
        
        for j in range(10):
            linea = stdin.readline().split()
            page  = linea[0]
            score = int(linea[1])
            
            lista.append((page, score))

            if score > max_:
                max_ = score
                
        print("Case #{}:".format(i + 1)) 
        for page, score in lista:
            if score == max_:
                print(page)
main()
