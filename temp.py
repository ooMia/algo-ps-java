h, w = 1000, 1000
k = 10
p = 8
print(h, w, k)
for r in range(h):
    print(''.join(str(1 - (
        c == 0 or r == h - 1 or r % p == 0
    )) for c in range(w)))