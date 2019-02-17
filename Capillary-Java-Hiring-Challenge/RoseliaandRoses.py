#!/bin/python

import os
import sys

if __name__ == '__main__':
    t = int(input())
    for _ in range(0,t):
        c = int(input())
        hq = list(map(int, input().rstrip().split()))
