#!/usr/bin/env python
# -*- coding: utf-8 -*-
import fileinput
import string
import chardet
import sys

def process(line):
  line = line.translate(trans_table)
  pos = 0
  for c in line:
    o = ord(c)
#    if o > 128 or o < 32:
#      print "Warning on char: ord(%s)=%s=%s" % (c,o,hex(o))
#      print " "*pos + "v"
#      print line
    pos += 1
  return line
  


def get_trans_table():
  tab = {}
  tab[0x2019] = ord(u'\'')
  tab[0x201c] = ord(u'"')
  tab[0x201d] = ord(u'"')
  tab[0x201e] = ord(u'"')
  tab[0x9] = ord(u' ')
  tab[0x2013] = ord(u'-')
  tab[0xa0] = ord(u' ')
  tab[0x2018] = ord(u'\'')
  return tab
  

trans_table = get_trans_table()

detected = chardet.detect(open(sys.argv[1],'rb').read())
enc = detected['encoding']


for line in fileinput.input(openhook=fileinput.hook_encoded(enc)):
  line = line.strip()
#  line = unicode(line)
  try:
    print process(line)
  except Exception as e:
    print e
    print line
