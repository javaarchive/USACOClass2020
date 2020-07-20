import sys
A = int(sys.argv[1])
B = int(sys.argv[2])
count = 0
for line in open('intresting.txt'):
    num = int(line)
    if A <= num and num <= B:
        print(num)
        count += 1
print(count)