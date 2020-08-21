from queue import Queue
table = {
    "A": 1,
    "2": 2,
    "3": 3,
    "4": 4,
    "5": 5,
    "6": 6,
    "7": 7,
    "8": 8,
    "9": 9,
    "T": 10,
    "J":11,
    "Q":12,
    "K": 13
}
N = int(input())
cards = []
for i in range(N):
    cards.append([])
    for j in range(N):
        cards[i].append([0])
for i in range(N):
    split = input().split(" ")
    for j in range(N):
        cards[N - i - 1][j] = table[split[j][0]]
bq = Queue() # not to be confused with bqi for benjeman qi

class BFSItem:
    def __init__(self, x,y, score):
        self.x = x
        self.y = y
        self.score = score
bq.put(BFSItem(0,0,cards[0][0]))
best = 0
while not bq.empty():
    item = bq.get()
    #print(str(item.x) + " "+str(item.y)+" "+str(item.score))
    if item.x == (N-1) and item.y == (N-1):
        #print(item.score)
        best = max(best, item.score)
    if item.x < N-1:
        bq.put(BFSItem(item.x + 1, item.y, item.score + cards[item.x + 1][item.y]))
    if item.y < N-1:
        bq.put(BFSItem(item.x, item.y + 1, item.score + cards[item.x][item.y + 1]))
    
print(best)