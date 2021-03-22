import random
import sys
# print(sys.argv)
count = int(sys.argv[1])
capacity = int(sys.argv[2])
print(str(capacity) + " " + str(count))
for i in range(count):
    print(random.randint(1,capacity - 1))