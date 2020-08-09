N, B = map(int, input().split())
lighting = []
for i in range(N):
    lighting.append("1" == (input()))
#print(len(lighting))
def toInt(arr):
    out = 0
    for x in arr:
        if x:
            add = 1
        else:
            add = 0
        out = 2 * out + add
    return out
precalc = []
seen = set()
loop = 0
last = toInt(lighting)
for j in range(B + 2):
    #print("T = "+str(x).ljust(5)+" ",end = "")
    prevstate = lighting[:]
    for i in range(-1,len(lighting) - 1):
        if prevstate[i % N]:
            if lighting[i+1] == False:
                lighting[i+1] = True
            else:
                lighting[i+1] = False
    num = toInt(lighting)
    if num in seen:
        #print("Seen at "+str(i))
        loop = len(precalc)
        break
    seen.add(num)
    precalc.append(num)
    
    for x in lighting:
        if x:
            bit = 1
        else:
            bit = 0
        #print(bit, end="")
    #print(" : "+str(num))
for x in (str(bin(precalc[(B - 2) % len(precalc)]))[2:]).zfill(N):
    print(x)