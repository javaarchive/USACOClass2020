full = input()
target = input()
K = len(target)
buf = ""
count = 0
for x in full:
    buf += x
    count += 1
    if count >= K:
        #print(buf[-K:])
        if buf[-K:] == target:
            buf = buf[:-K]
            #print("Removed")
print(buf)