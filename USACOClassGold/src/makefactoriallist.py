x = 1
nums = []
for i in range(1,20 + 1):
    x = x * i
    nums.append(x)
print(",".join(map(str,nums)))