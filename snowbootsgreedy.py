N, B = list(map(int,input().split(" ")))
tiledepths = list(map(int,input().split(" ")))
depths = []
speeds = []
for x in range(B):
    depth,stepspeed = list(map(int,input().split(" ")))
    depths.append(depth)
    speeds.append(stepspeed)
curboot = -1
for x in tiledepths:
    if curboot < 0 or depths[curboot] < depth:
        while curboot < 0 or depths[curboot] < depth:
            curboot = curboot + 1
print(curboot-1)