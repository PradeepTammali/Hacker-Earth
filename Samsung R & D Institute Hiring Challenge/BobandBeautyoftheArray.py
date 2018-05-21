from itertools import combinations
import numpy as np
# import sys

# def subs(l):
#     if l == []:
#         return [[]]
#     x = subs(l[1:])
#     return x + [[l[0]] + y for y in x]

if __name__ == '__main__':
    sum = 0
    N = int(input().rstrip())
    arr = list(map(int, input().rstrip().split()))
    for i in range(2, N+1):
        subsets = list(combinations(arr, i))
        for subset in subsets:
            ar = np.array(subset)
            sum += np.bitwise_or.reduce(ar)
    # subsets = subs(arr)
    # for i in range(len(subsets)):
    #     if len(subset) >= 2:
    #         ar = np.array(subset)
    #         sum += np.bitwise_or.reduce(ar)
    print(sum%1000000007)