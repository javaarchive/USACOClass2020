nums = list(map(int,input().split(" ")))
nums = set(nums)
for a in range(15):
    for b in range(a + 1, 15):
        for c in range(b + 1, 15):
           if len(({a,b,c,a+b,b+c,a+c,a+b+c}).intersection(nums)) == len(nums):
               print(a,b,c)