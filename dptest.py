precalc = [None] * 25
limit = 11
total = 0

def S(N):
    global total
    if N == -1:
        return 0
    if precalc[N] != None:
        return precalc[N]
    res = S(N-1) + 2**(N+1)-1+ S(N-1)
    if (total + S(N-1)) < limit:
        total += S(N-1)
    center = 2**(N+1)-1
    if (total + center) < limit:
        total += center
    if (total + S(N-1)) < limit:
        total += S(N-1)
    precalc[N] = res
    return res
print(S(4))
print(total)