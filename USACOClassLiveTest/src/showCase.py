N = 200
arr = []
for x in range(N):
    arr.append([])
    for y in range(N):
        arr[x].append(".")
def toOffset(x,y):
    return x+100,y+100
ints = list(map(int, input().split(" ")))
bx, by = toOffset(ints[1],ints[2])
arr[bx][by] = "B"
for i in range(ints[0]):
    inputArr = input().split(" ")
    x,y = toOffset(int(inputArr[0]), int(inputArr[1]))
    #print(x,y)
    arr[x][y] = inputArr[2]
ax, ay = toOffset(0,0)
arr[ax][ay] = "A"
for Y in range(N-1,0, -1):
    #print(X)
    for X in range(N):
        print(arr[X][Y], end="")
    print()
