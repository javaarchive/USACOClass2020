initialState = "10000"
lighting = list(map(int,list(initialState)))
for x in range(200):
    print("T = "+str(x).ljust(5)+" ",end = "")
    prevstate = lighting[:]
    for i in range(-1,len(lighting) - 1):
        if prevstate[i % len(lighting)]:
            if lighting[i+1] == 0:
                lighting[i+1] = 1
            else:
                lighting[i+1] = 0
    display = map(str, lighting)
    print("".join(display))