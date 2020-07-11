precalc = [None] * 25
def S(N):
    if N == -1:
        return ""
    if precalc[N] != None:
        return precalc[N]
    res = S(N-1) + "m" + "o" * (N+2)+ S(N-1)
    precalc[N] = res
    return res
print(S(8))