precalc = [None] * 25
ms = [1]
total = 1
def S(N):
    global total
    if N == -1:
        return 0
    print("S("+str(N)+")")
    if precalc[N] != None:
        total += precalc[N]
        ms.append(total)
        return precalc[N]
    res = S(N-1) + 1 + (N+2)+ S(N-1)
    total += S(N-1)
    ms.append(total)
    total += 1 + (N+2)
    ms.append(total)
    total += res
    ms.append(total)
    precalc[N] = res
    return res
print(S(5))

print(ms)