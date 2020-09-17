N, K = list(map(int,input().split())) # 10/10 IO
class BFSItem:
    def __init__(self, mins: int, pos: int):
        '''
        Very old school python haven't bothered to learn new stuff
        '''
        self.mins = mins
        self.pos = pos
bfsqueue = []
bfsqueue.append(BFSItem(0, N));
v = [None] * 100002
v[N] = True
while not len(bfsqueue) == 0:
    bi: BFSItem = bfsqueue.pop(0)
    #print(bi.mins,bi.pos)
    if bi.pos == K:
        print(bi.mins)
        break
    if (2 * bi.pos) - K < (K - bi.pos) and not bi.pos == 0:
        v[(2 * bi.pos)] = True
        bfsqueue.append(BFSItem(bi.mins + 1, bi.pos * 2))
    if bi.pos + 1 <= K and not v[bi.pos + 1]:
        v[bi.pos + 1] = True
        bfsqueue.append(BFSItem(bi.mins + 1, bi.pos + 1));
    if bi.pos >= 0 and not v[bi.pos - 1]:
        v[bi.pos - 1] = True
        bfsqueue.append(BFSItem(bi.mins + 1, bi.pos - 1));
