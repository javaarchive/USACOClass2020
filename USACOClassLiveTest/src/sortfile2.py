import sys
filename = sys.argv[1]
outfile = sys.argv[2]
lines = open(filename, "r").readlines()
def sortByFirstNumber(line):
    #print(line.split(" "))
    return int((line.split(" "))[0])
lines = sorted(lines, key = sortByFirstNumber)
ofile = open(outfile, "w")
for num in lines:
    ofile.write(str(num))
    