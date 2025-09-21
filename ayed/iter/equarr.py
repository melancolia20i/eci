n = int(input())
a = list(map(int, input().split()))
a = sorted(a)

ans = n - 1

for i in range(n):
    j = i
    while j < n and a[i] == a[j]:
        j += 1
    ans = min(ans, n - (j - i))

print(ans)
