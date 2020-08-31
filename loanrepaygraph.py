import matplotlib.pyplot as plt
import matplotlib
#matplotlib.use('AGG')
import numpy as np
import math

N = 50
K = 3
M = 3

#X = 3
#fig, ax = plt.subplots()
for X in range(1,7):
    G = 0
    xaxis = [0]
    yaxis = [0]
    for i in range(10):
        xaxis.append(i + 1)
        G += max(math.floor((N-G)/X), M)
        yaxis.append(G)
        if G > N:
            break
    plt.plot(xaxis,yaxis)
'''
xaxis = []
yaxis = []
for x in range(10):
    xaxis.append(x)
    yaxis.append(math.sqrt(2 * x))
plt.plot(xaxis,yaxis)
'''