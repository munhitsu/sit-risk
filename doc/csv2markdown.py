#!/usr/bin/env python

import csv
import sys
print csv.list_dialects()

if len(sys.argv[1:]) > 0:
    fin = open(sys.argv[1], 'rb')
else:
    fin = sys.stdin
csvReader = csv.reader(fin, dialect='excel')
lineNumber = 0
for row in csvReader:
    print "| %s |" % " | ".join(row)
    if lineNumber == 0:
        print "| --- |"
    lineNumber += 1
