N = int(input().split(" ")[0])
cows = {}
for x in range(N):
    inp = list(map(int, input().split(" ")))
    if not inp[1] in cows.keys():
        cows[inp[1]] = 500
    cows[inp[1]] += inp[2]
    print(max(cows.values()))
