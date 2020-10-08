fline = list(map(int,input().split(" ")))
N = fline[0]
R = fline[3]
spaces = 2
def fillStr(instr, a, b,content):
    instr = list(instr)
    for x in range(a, b):
        instr[x] = content
    return "".join(instr)
for x in range(N):
    print(str(x).ljust(spaces, " ")+" ",end="")
    print(" ",end="")
print()
fullstr = " "*((spaces + 1) * N ) + " " * 2
for i in range(R):
    lined = list(map(int,input().split(" ")))
    print(fillStr(fullstr, lined[0] * (spaces + 1) + 1, lined[1] * (spaces + 1), "-"))