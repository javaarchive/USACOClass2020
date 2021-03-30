A = input("List? ").split(" ")
sorted_ = False
moos = 0
while not sorted_:
    moos += 1
    sorted_ = True
    for i in range(0, len(A) - 1):
        if A[i + 1] < A[i]:
            # Swap
            temp = A[i]
            A[i] = A[i + 1]
            A[i + 1] = temp
            sorted_ = False
print("Moo Count:",moos)