#!/usr/bin/python

import argparse
import sys

parser = argparse.ArgumentParser(description='Processes c++ code and adds stuff based on comments.')
parser.add_argument("source", type=str,
                    help="file to process")
parser.add_argument("-v", "--verbose", action="store_true",
                    help="increase output verbosity")

args = parser.parse_args(sys.argv[1:])

print("Opening",args.source)
f = open(args.source, 'r')
lines = f.readlines()
outlines = []

timers = set()

head_pos = 0

for line in lines:
    if line.startswith("using namespace std"):
        head_pos = len(outlines)
    if line.replace("   ","").replace(" ","").startswith("//"):
         # print("Got commented line",line)
         cleanLine = line.replace("   ","").replace("/ @","/@")
         if cleanLine.startswith("//@"):
             # options
             lineData = cleanLine.split(" ")
             if lineData[0] == "//@timer":
                timerName = lineData[1]
                if timerName in timers:
                    timers.discard(timerName)
                else:
                    timers.add(timerName)

    # else
    outlines.append(line)
f.close()