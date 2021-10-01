# I dislike this input format and it would be a pain to do in c++
import queue
try:
    for _ in range(5):
        line = input()
        nums = list(map(int,line.split(", ")))
        total = nums[0]
        count = 0
        cards = nums[1:]
        dealer = queue.Queue()
        player = queue.Queue()
        for i in range(len(cards)):
            if 0 <= i and i <= 2:
                player.put(cards[i])
            elif i % 2 == 0:
                player.put(cards[i])
            else:
                dealer.put(cards[i])
        
        for i in range(10):
            card = 0
            if i % 2 == 0:
                card = player.get()
            else:
                card = dealer.get()
            if card == 9:
                pass
            elif card == 4:
                total -= 10
            elif card == 0:
                if total + 11 > 99:
                    total += 1
                else:
                    total += 11
            else:
                total += card
            if total > 99:
                if card % 2 == 0:
                    print(str(total) + ", player")
                else:
                    print(str(total) + ", dealer")
                break
                
            
except Exception as e:
    # EOF Error so no more input
    pass