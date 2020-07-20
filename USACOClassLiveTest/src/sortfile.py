import sys
filename = sys.argv[1]
outfile = sys.argv[2]
lines = map(int,open(filename, "r").read().splitlines())
lines = sorted(lines)
ofile = open(outfile, "w")
for num in lines:
    ofile.write(str(num))
    